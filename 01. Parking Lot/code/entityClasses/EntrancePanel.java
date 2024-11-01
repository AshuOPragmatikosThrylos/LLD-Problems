package entityClasses;

import java.time.LocalDateTime;

import abstractClasses.AbstractParkingSpot;
import builders.TicketBuilder;

public class EntrancePanel {
    // private String id;

    public EntrancePanel(String id) {
        // this.id = id;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        AbstractParkingSpot spot = ParkingLotSingleton.INSTANCE.getAvailableSpot(vehicle.getType());
        if (spot != null) {
            spot.occupySpot();
            return new TicketBuilder(vehicle)
                    .withParkingSpot(spot)
                    .withEntryTime(LocalDateTime.now())
                    .build();
        }
        return null;
    }
}