package entityClasses;
import enumerations.PaymentMethod;
import interfaces.RateStrategyInterface;
import payment.StepwisePaymentProcessor;

public class ExitPanel {
    // private String id;

    public ExitPanel(String id) {
        // this.id = id;
    }

    public boolean processPayment(Ticket ticket, PaymentMethod method, RateStrategyInterface rateStrategy) {
        return new StepwisePaymentProcessor().processPayment(ticket, method, rateStrategy);
    }
}
