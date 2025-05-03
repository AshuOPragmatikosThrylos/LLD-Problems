package elevatorMovementStrategyImpl;

import java.util.Collections;
import java.util.concurrent.ConcurrentSkipListSet;

import Interfaces.ElevatorMovementStrategy;
import src.Elevator;
import src.Request;
import enums.Direction;

public class Scan implements ElevatorMovementStrategy {
    // ConcurrentSkipListSet preferred over TreeSet cuz it's thread-safe within each elevator
    private ConcurrentSkipListSet<Integer> upQueue = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> downQueue = new ConcurrentSkipListSet<>(Collections.reverseOrder());
    private int minFloor;
    private int maxFloor;

    public Scan(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int requestFloor = request.getFloor();
        if (requestFloor > elevator.getCurrentFloor()) {
            upQueue.add(requestFloor);
        } else {
            downQueue.add(requestFloor);
        }
    }

    @Override
    public void move(Elevator elevator) {
        if (elevator.getDirection() == Direction.IDLE) {
            if (!upQueue.isEmpty()) {
                elevator.setDirection(Direction.UP);
            } else if (!downQueue.isEmpty()) {
                elevator.setDirection(Direction.DOWN);
            }
        }

        if (elevator.getDirection() == Direction.UP) {
            elevator.setCurrentFloor(elevator.getCurrentFloor()+1);
            if (upQueue.remove(elevator.getCurrentFloor())) {
                System.out.println("Elevator " + elevator.getId() + " stopped at " + elevator.getCurrentFloor());
            }

            if (elevator.getCurrentFloor() == maxFloor) {
                elevator.setDirection(Direction.DOWN);
            }

        } else if (elevator.getDirection() == Direction.DOWN) {
            elevator.setCurrentFloor(elevator.getCurrentFloor()-1);
            if (downQueue.remove(elevator.getCurrentFloor())) {
                System.out.println("Elevator " + elevator.getId() + " stopped at " + elevator.getCurrentFloor());
            }

            if (elevator.getCurrentFloor() == minFloor) {
                elevator.setDirection(Direction.UP);
            }
        }
    }

    @Override
    public ElevatorMovementStrategy clone() {
        return new Scan(minFloor, maxFloor);
    }
}

// Why TreeSet and not PriorityQueue?
// Let's say elevator is at floor 6 and moving up
// now a request comes from floor 5 to go up
// now when elevator reaches floor 7 if it was in upQueue 
// we want to remove it as it has already been served
// but floor 7 cannot be accessed from PQ
// we'll be able to access only floor 5
// thus we'll get wrong results with the use of PQ

