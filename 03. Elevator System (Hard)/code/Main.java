import enums.Direction;
import enums.RequestType;
import src.ElevatorSystem;
import src.Request;
import Interfaces.ElevatorAssignmentStrategy;
import Interfaces.ElevatorMovementStrategy;
import elevatorAssignmentStrategyImpl.NearestElevator;
// import elevatorMovementStrategyImpl.Scan;
import elevatorMovementStrategyImpl.ScanAndLookAhead;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ElevatorAssignmentStrategy assignmentStrategy = new NearestElevator();
        // ElevatorMovementStrategy movementStrategy = new Scan(0, 10);
        ElevatorMovementStrategy movementStrategy = new ScanAndLookAhead();
        ElevatorSystem system = new ElevatorSystem(2, assignmentStrategy, movementStrategy);

        system.handleHallCall(new Request(3, Direction.UP, RequestType.HALL_CALL));
        system.handleHallCall(new Request(6, Direction.DOWN, RequestType.HALL_CALL));
        system.handleCarCall(0, 8);
        system.handleCarCall(1, 2);

        // Monitor displays every 2 seconds
        Thread monitorThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    System.out.println("---- Hall Displays ----");
                    system.getElevators().get(0).showHallDisplay();
                    system.getElevators().get(1).showHallDisplay();
                    System.out.println("------------------------");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        monitorThread.start();

        // Let simulation run for 15 seconds
        Thread.sleep(15000);
        system.shutdown();
        monitorThread.interrupt(); // stop display thread
    }
}
