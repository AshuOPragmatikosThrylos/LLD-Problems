package spotImpl;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;

public class BikeSpot extends AbstractParkingSpot {
    public BikeSpot(String id, ParkingFloor floor) {
        super(id, VehicleType.BIKE, floor);
    }
}