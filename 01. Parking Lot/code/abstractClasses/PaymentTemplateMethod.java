package abstractClasses;

import entityClasses.Ticket;
import enumerations.PaymentMethod;
import interfaces.RateStrategyInterface;

public abstract class PaymentTemplateMethod {
    public final boolean processPayment (Ticket ticket, PaymentMethod method, RateStrategyInterface rateStrategy) { // template method
        if (!validateTicket(ticket)) {
            return false;
        }
        double amount = calculateAmount(ticket, rateStrategy);
        return makePayment(amount, method);
    }

    protected abstract boolean validateTicket(Ticket ticket);
    protected abstract double calculateAmount(Ticket ticket, RateStrategyInterface rateStrategy);
    protected abstract boolean makePayment(double amount, PaymentMethod method);
}

// Here template method design pattern was not really needed
// cuz we don't want to enforce step order
// ACROSS MULTIPLE SUBCLASSES
