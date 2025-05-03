package src;

import enums.Direction;

public class Display {
    private int currentFloor;
    private Direction direction;

    public synchronized void update(int floor, Direction direction) {
        this.currentFloor = floor;
        this.direction = direction;
    }

    public synchronized void showInsideDisplay(int elevatorId) {
        System.out.println("Inside Elevator " + elevatorId + ": Floor " + currentFloor + ", Direction: " + direction);
    }

    public synchronized void showOutsideDisplay(int elevatorId) {
        System.out.println("Hall Display for Elevator " + elevatorId + ": Currently at floor " + currentFloor + ", Direction: " + direction);
    }
}
