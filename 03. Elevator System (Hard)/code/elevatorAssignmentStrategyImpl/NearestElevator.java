package elevatorAssignmentStrategyImpl;

import java.util.List;

import Interfaces.ElevatorAssignmentStrategy;
import src.Elevator;
import src.Request;

public class NearestElevator implements ElevatorAssignmentStrategy {
    @Override
    public Elevator assignElevator(List<Elevator> elevators, Request request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - request.getFloor());
            if (distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }
        return best;
    }
}
