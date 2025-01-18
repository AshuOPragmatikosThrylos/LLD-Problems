package entityClasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstractClasses.AbstractParkingSpot;
import enumerations.VehicleType;

public class ParkingFloor {
    // private String id;
    private List<AbstractParkingSpot> spots;
    private Map<VehicleType, Integer> availableSpotsCount;

    public ParkingFloor(String id, List<AbstractParkingSpot> spots) {
        // this.id = id;
        this.spots = spots;
        this.availableSpotsCount = new HashMap<>();
    }

    public void addSpot(AbstractParkingSpot spot) {
        spots.add(spot);
        availableSpotsCount.put(
                spot.getVehicleType(),
                availableSpotsCount.getOrDefault(spot.getVehicleType(), 0) + 1);
    }

    public void updateSpotCount(VehicleType type, boolean increase) {
        int count = availableSpotsCount.getOrDefault(type, 0);
        if (increase) {
            availableSpotsCount.put(type, count + 1);
        } else if (count > 0) {
            availableSpotsCount.put(type, count - 1);
        }
    }

    public AbstractParkingSpot getAvailableSpot(VehicleType vehicleType) {
        for (AbstractParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicleType) {
                return spot;
            }
        }
        return null;
    }

    public Map<VehicleType, Integer> getAvailableSpotsCount() {
        return availableSpotsCount;
    }
}