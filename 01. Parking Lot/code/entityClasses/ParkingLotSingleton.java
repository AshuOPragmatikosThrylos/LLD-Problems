package entityClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstractClasses.AbstractParkingSpot;
import enumerations.VehicleType;

public enum ParkingLotSingleton {
    INSTANCE;

    private List<ParkingFloor> floors;
    private List<EntrancePanel> entrancePanels;
    private List<ExitPanel> exitPanels;
    private DisplayBoard displayBoard;

    // Constructor of enum behaves like a private constructor
    ParkingLotSingleton() {
        this.floors = new ArrayList<>();
        this.entrancePanels = new ArrayList<>();
        this.exitPanels = new ArrayList<>();
        this.displayBoard = new DisplayBoard();
    }

    public List<ParkingFloor> getFloors() {
        return this.floors;
    }

    public List<EntrancePanel> getEntrancePanels() {
        return this.entrancePanels;
    }

    // public List<ExitPanel> getExitPanels() {
    // return this.exitPanels;
    // }

    public void addEntrancePanel(EntrancePanel panel) {
        entrancePanels.add(panel);
    }

    public void addExitPanel(ExitPanel panel) {
        exitPanels.add(panel);
    }

    public AbstractParkingSpot getAvailableSpot(VehicleType vehicleType) {
        for (ParkingFloor floor : floors) {
            AbstractParkingSpot spot = floor.getAvailableSpot(vehicleType);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }

    public void updateDisplay() {
        int totalCarSpots = 0;
        int totalBikeSpots = 0;
        int totalTruckSpots = 0;
        int totalEVSpots = 0;

        for (ParkingFloor floor : floors) {
            Map<VehicleType, Integer> floorCounts = floor.getAvailableSpotsCount();
            totalCarSpots += floorCounts.getOrDefault(VehicleType.CAR, 0);
            totalBikeSpots += floorCounts.getOrDefault(VehicleType.BIKE, 0);
            totalTruckSpots += floorCounts.getOrDefault(VehicleType.TRUCK, 0);
            totalEVSpots += floorCounts.getOrDefault(VehicleType.EV, 0);
        }

        displayBoard.updateAvailableSpots(totalCarSpots, totalBikeSpots, totalTruckSpots, totalEVSpots);
    }
}
