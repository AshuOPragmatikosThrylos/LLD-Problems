package spotImpl;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;

public class TruckSpot extends AbstractParkingSpot {
    public TruckSpot(String id, ParkingFloor floor) {
        super(id, VehicleType.TRUCK, floor);
    }
}