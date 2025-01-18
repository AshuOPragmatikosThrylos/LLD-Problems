package builders;

import java.time.LocalDateTime;

import abstractClasses.AbstractParkingSpot;
import entityClasses.Ticket;
import entityClasses.Vehicle;

public class TicketBuilder {
    Ticket ticket;

    public TicketBuilder(Vehicle vehicle) {
        getTicket().setVehicle(vehicle);
    }

    protected Ticket getTicket() {
        if (ticket == null) {
            ticket = new Ticket();
        }
        return ticket;
    }

    public TicketBuilder withParkingSpot(AbstractParkingSpot parkingSpot) {
        getTicket().setParkingSpot(parkingSpot);
        return this;
    }

    public TicketBuilder withEntryTime(LocalDateTime entryTime) {
        getTicket().setEntryTime(entryTime);
        return this;
    }

    public Ticket build() {
        return getTicket();
    }
}