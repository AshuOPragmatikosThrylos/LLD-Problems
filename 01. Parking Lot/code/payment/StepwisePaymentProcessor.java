package payment;

import java.time.Duration;
import abstractClasses.PaymentTemplateMethod;
import entityClasses.Ticket;
import enumerations.PaymentMethod;
import interfaces.RateStrategyInterface;
import managers.ParkingRateManager;

public class StepwisePaymentProcessor extends PaymentTemplateMethod {

    public boolean validateTicket(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Invalid ticket.");
            return false;
        }
        return true;
    }

    public double calculateAmount(Ticket ticket, RateStrategyInterface rateStrategy) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        ParkingRateManager rateManager = new ParkingRateManager(rateStrategy);
        return rateManager.calculateParkingCharge(duration);
    }

    public boolean makePayment(double amountToPay, PaymentMethod method) {
        switch (method) {
            case CASH:
                System.out.println("Payment of $" + amountToPay + " processed using CASH.");
                break;
            case CREDIT_CARD:
                System.out.println("Payment of $" + amountToPay + " processed using CREDIT CARD.");
                break;
            case DEBIT_CARD:
                System.out.println("Payment of $" + amountToPay + " processed using DEBIT CARD.");
                break;
            case DIGITAL_WALLET:
                System.out.println("Payment of $" + amountToPay + " processed using DIGITAL WALLET.");
                break;
            default:
                System.out.println("Invalid payment method.");
                return false;
        }

        return true;
    }
}
