package factories;

import abstractClasses.AbstractParkingSpot;
import entityClasses.ParkingFloor;
import enumerations.VehicleType;
import spotImpl.BikeSpot;
import spotImpl.CarSpot;
import spotImpl.TruckSpot;
import spotImpl.EvSpot;

public class ParkingSpotFactory {
    public static AbstractParkingSpot createSpot(VehicleType type, String id, ParkingFloor floor) {
        switch (type) {
            case CAR:
                return new CarSpot(id, floor);
            case BIKE:
                return new BikeSpot(id, floor);
            case TRUCK:
                return new TruckSpot(id, floor);
            case EV:
                return new EvSpot(id, floor);
            default:
                return null;
        }
    }
}
