package src;

import enums.Direction;
import enums.RequestType;

public class Request {
    int floor;
    Direction direction;
    RequestType type;

    public Request(int floor, Direction direction, RequestType type) {
        this.floor = floor;
        this.direction = direction;
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }
}
