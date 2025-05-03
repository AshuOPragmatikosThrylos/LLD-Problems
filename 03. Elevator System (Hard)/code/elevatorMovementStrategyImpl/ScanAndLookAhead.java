package elevatorMovementStrategyImpl;

import java.util.Collections;
import java.util.concurrent.ConcurrentSkipListSet;

import Interfaces.ElevatorMovementStrategy;
import src.Elevator;
import src.Request;
import enums.Direction;

public class ScanAndLookAhead implements ElevatorMovementStrategy {
    private ConcurrentSkipListSet<Integer> upQueue = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> downQueue = new ConcurrentSkipListSet<>(Collections.reverseOrder());

    @Override
    public void addRequest(Elevator elevator, Request request) {
        int currentFloor = elevator.getCurrentFloor();
        int requestFloor = request.getFloor();
        if (requestFloor > currentFloor) {
            upQueue.add(requestFloor);
        } else if (requestFloor < currentFloor) {
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
            if (upQueue.isEmpty() && !downQueue.isEmpty()) {
                elevator.setDirection(Direction.DOWN);
            } else if (upQueue.isEmpty()) {
                elevator.setDirection(Direction.IDLE);
            }

        } else if (elevator.getDirection() == Direction.DOWN) {
            elevator.setCurrentFloor(elevator.getCurrentFloor()-1);
            if (downQueue.remove(elevator.getCurrentFloor())) {
                System.out.println("Elevator " + elevator.getId() + " stopped at " + elevator.getCurrentFloor());
            }
            if (downQueue.isEmpty() && !upQueue.isEmpty()) {
                elevator.setDirection(Direction.UP);
            } else if (downQueue.isEmpty()) {
                elevator.setDirection(Direction.IDLE);
            }
        }
    }

    @Override
    public ElevatorMovementStrategy clone() {
        return new ScanAndLookAhead();
    }
}
