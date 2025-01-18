import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entityClasses.EntrancePanel;
import entityClasses.ExitPanel;
import entityClasses.ParkingFloor;
import entityClasses.ParkingLotSingleton;
import entityClasses.Ticket;
import entityClasses.Vehicle;
import enumerations.PaymentMethod;
import enumerations.VehicleType;
import factories.ParkingSpotFactory;
import interfaces.RateStrategyInterface;
import managers.ParkingRateManager;
// import strategies.FlatRateStrategyImpl;
import strategies.HourlyRateStrategyImpl;

public class Main {
    public static void main(String[] args) {
        ParkingLotSingleton parkingLot = ParkingLotSingleton.INSTANCE;
    
        setupParkingFloors(parkingLot);
        setupEntranceAndExitPanels(parkingLot);
    
        processVehicle("KA01-1234", VehicleType.CAR, parkingLot);
        printSeparator();

        processVehicle("KA04-2435", VehicleType.BIKE, parkingLot);
        printSeparator();
        
        processVehicle("KA02-5678", VehicleType.TRUCK, parkingLot); // Handicap spot
        printSeparator();
        
        processVehicle("KA03-9101", VehicleType.EV, parkingLot);
    }

    private static void processVehicle(String vehicleNumber, VehicleType vehicleType, ParkingLotSingleton parkingLot) {
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
        EntrancePanel entrancePanel = parkingLot.getEntrancePanels().get(0);
        Ticket ticket = entrancePanel.generateTicket(vehicle);
        processTicket(ticket);
    }

    private static void printSeparator() {
        System.out.println("-----------------------------------------------------");
    }
    
    private static void setupParkingFloors(ParkingLotSingleton parkingLot) {
        ParkingFloor floor1 = createFloorWithSpots("Floor1");
        ParkingFloor floor2 = createFloorWithSpots("Floor2");
    
        parkingLot.getFloors().add(floor1);
        parkingLot.getFloors().add(floor2);
    }
    
    private static ParkingFloor createFloorWithSpots(String floorName) {
        ParkingFloor floor = new ParkingFloor(floorName, new ArrayList<>());
        addSpotsToFloor(floor, floorName);
        return floor;
    }
    
    private static void addSpotsToFloor(ParkingFloor floor, String floorName) {
        if (floorName.equals("Floor1")) {
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.CAR, "C1", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.BIKE, "B1", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.CAR, "C2", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.BIKE, "B2", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.EV, "E1", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.CAR, "C4", floor));
        } else if (floorName.equals("Floor2")) {
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.TRUCK, "T1", floor));
            floor.addSpot(ParkingSpotFactory.createSpot(VehicleType.CAR, "C3", floor)); // handicap spot
        }
    }    
    
    private static void setupEntranceAndExitPanels(ParkingLotSingleton parkingLot) {
        EntrancePanel entrancePanel = new EntrancePanel("Entrance1");
        ExitPanel exitPanel = new ExitPanel("Exit1");
    
        parkingLot.addEntrancePanel(entrancePanel);
        parkingLot.addExitPanel(exitPanel);
    }
    
    private static void processTicket(Ticket ticket) {
        if (ticket == null) {
            System.out.println("No available parking spots for this vehicle type.");
            return;
        }
    
        displayTicketDetails(ticket);
    
        // Simulate some time passing
        ticket.setExitTime(LocalDateTime.now().plusHours(2));
        double parkingCharge = calculateParkingCharge(ticket);
        System.out.println("Parking Charge: $" + parkingCharge);
    
        processPayment(ticket, parkingCharge);
    }
    
    private static void displayTicketDetails(Ticket ticket) {
        System.out.println("Ticket Generated!");
        System.out.println("Vehicle Type: " + ticket.getParkingSpot().getVehicleType());
        System.out.println("Spot ID: " + ticket.getParkingSpot().getId());
        System.out.println("Entry Time: " + ticket.getEntryTime());
    }
    
    private static double calculateParkingCharge(Ticket ticket) {
        // RateStrategyInterface flatRateStrategy = new FlatRateStrategyImpl(50.0, ticket.getVehicle().getType());
        RateStrategyInterface hourlyRateStrategy = new HourlyRateStrategyImpl(60.0, ticket.getVehicle().getType());
        ParkingRateManager rateManager = new ParkingRateManager(hourlyRateStrategy);
    
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        System.out.println("Parking Duration: " + duration.toHours() + " hours");
    
        return rateManager.calculateParkingCharge(duration);
    }
    
    private static void processPayment(Ticket ticket, double charge) {
        ExitPanel exitPanel = new ExitPanel("Exit1");
        // RateStrategyInterface flatRateStrategy = new FlatRateStrategyImpl(50.0, ticket.getVehicle().getType());
        RateStrategyInterface hourlyRateStrategy = new HourlyRateStrategyImpl(60.0, ticket.getVehicle().getType());
        // boolean paymentSuccess = exitPanel.processPayment(ticket, PaymentMethod.CREDIT_CARD, hourlyRateStrategy);
        boolean paymentSuccess = exitPanel.processPayment(ticket, PaymentMethod.DIGITAL_WALLET, hourlyRateStrategy);
    
        if (paymentSuccess) {
            System.out.println("Payment Successful! Exiting the parking lot.");
            ticket.getParkingSpot().freeSpot();
        } else {
            System.out.println("Payment Failed. Please try again.");
        }
    }
    
    
}