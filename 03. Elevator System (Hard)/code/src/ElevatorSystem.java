package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Interfaces.ElevatorAssignmentStrategy;
import Interfaces.ElevatorMovementStrategy;
import enums.RequestType;

public class ElevatorSystem {
    List<Elevator> elevators = new ArrayList<>();
    ExecutorService executor = Executors.newCachedThreadPool();
    ElevatorAssignmentStrategy assignmentStrategy;
    ElevatorMovementStrategy movementStrategy;

    public ElevatorSystem(int numElevators, ElevatorAssignmentStrategy assignmentStrategy, ElevatorMovementStrategy movementStrategy) {
        this.assignmentStrategy = assignmentStrategy;
        this.movementStrategy = movementStrategy;

        for (int i = 0; i < numElevators; i++) {
            Elevator e = new Elevator(i, movementStrategy.clone()); // clone to avoid shared state across elevators
            elevators.add(e);
            executor.submit(e);
        }
    }

    public void handleHallCall(Request request) {
        Elevator best = assignmentStrategy.assignElevator(elevators, request);
        best.addRequest(request);
    }

    public void handleCarCall(int elevatorId, int destinationFloor) {
        Elevator e = elevators.get(elevatorId);
        e.addRequest(new Request(destinationFloor, null, RequestType.CAR_CALL));
    }

    // executor service is shutdown only after all elevator threads are shutdown
    public void shutdown() {
        for (Elevator e : elevators) {
            e.shutdown();
        }
        executor.shutdown();
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
