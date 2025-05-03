package src;

import enums.Direction;
import Interfaces.ElevatorMovementStrategy;

public class Elevator implements Runnable {
    int id;
    volatile int currentFloor = 0;
    volatile Direction direction = Direction.IDLE;
    ElevatorMovementStrategy movementStrategy;
    volatile boolean running = true;
    Display display;

    public Elevator(int id, ElevatorMovementStrategy strategy) {
        this.id = id;
        this.movementStrategy = strategy;
        this.display = new Display();
    }

    public synchronized void addRequest(Request request) {
        movementStrategy.addRequest(this, request);
    }

    public void step() {
        movementStrategy.move(this);
        display.update(currentFloor, direction);
        display.showInsideDisplay(id);
    }

    public void showHallDisplay() {
        display.showOutsideDisplay(id);
    }

    public void shutdown() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            step();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}

// The main thread or scheduler may call shutdown() to stop the elevator
// Without volatile, the elevator thread running run() might never see that running was changed to false, and may keep running

