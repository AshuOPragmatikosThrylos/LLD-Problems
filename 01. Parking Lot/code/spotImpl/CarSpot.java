package spotImpl;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;

public class CarSpot extends AbstractParkingSpot {
    public CarSpot(String id, ParkingFloor floor) {
        super(id, VehicleType.CAR, floor);
    }
}