package entityClasses;

import java.time.LocalDateTime;

import abstractClasses.AbstractParkingSpot;

public class Ticket {
    private Vehicle vehicle;
    private AbstractParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingSpot(AbstractParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public AbstractParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    public LocalDateTime getExitTime() {
        return this.exitTime;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
