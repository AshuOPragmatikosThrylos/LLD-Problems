package abstractClasses;

import entityClasses.ParkingFloor;
import entityClasses.ParkingLotSingleton;
import enumerations.VehicleType;

public abstract class AbstractParkingSpot {
    private String id;
    private VehicleType vehicleType;
    private boolean isAvailable;
    private ParkingFloor floor;

    public AbstractParkingSpot(String id, VehicleType vehicleType, ParkingFloor floor) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
        this.floor = floor;
    }

    public void occupySpot() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Vehicle arrived at parking lot.");
            ParkingLotSingleton.INSTANCE.updateDisplay();
            floor.updateSpotCount(vehicleType, false);
        }
    }

    public void freeSpot() {
        if (!isAvailable) {
            isAvailable = true;
            floor.updateSpotCount(vehicleType, true);
            ParkingLotSingleton.INSTANCE.updateDisplay();
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getId() {
        return id;
    }
}