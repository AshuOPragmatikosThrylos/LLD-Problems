package spotImpl;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;

public class EvSpot extends AbstractParkingSpot {
    public EvSpot(String id, ParkingFloor floor) {
        super(id, VehicleType.EV, floor);
    }
}