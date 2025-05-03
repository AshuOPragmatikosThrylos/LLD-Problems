package Interfaces;

import java.util.List;

import src.Elevator;
import src.Request;

public interface ElevatorAssignmentStrategy {
    Elevator assignElevator(List<Elevator> elevators, Request request);
}
