package Interfaces;

import src.Elevator;
import src.Request;

public interface ElevatorMovementStrategy extends Cloneable {
    void addRequest(Elevator elevator, Request request);
    void move(Elevator elevator);
    ElevatorMovementStrategy clone();
}
