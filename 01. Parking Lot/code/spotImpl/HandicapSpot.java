package spotImpl;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;

public class HandicapSpot extends AbstractParkingSpot {
    public HandicapSpot(String id, ParkingFloor floor) {
        super(id, VehicleType.CAR, floor); // Assuming only cars for handicap spots
    }
}