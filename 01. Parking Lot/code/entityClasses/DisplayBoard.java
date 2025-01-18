package entityClasses;
public class DisplayBoard {
    private int availableCarSpots;
    private int availableBikeSpots;
    private int availableTruckSpots;
    private int availableEVSpots;

    public void updateAvailableSpots(int carSpots, int bikeSpots, int truckSpots, int evSpots) {
        this.availableCarSpots = carSpots;
        this.availableBikeSpots = bikeSpots;
        this.availableTruckSpots = truckSpots;
        this.availableEVSpots = evSpots;

        display();
    }

    private void display() {
        System.out.println("Available Spots - Cars: " + availableCarSpots +
                           ", Bikes: " + availableBikeSpots +
                           ", Trucks: " + availableTruckSpots +
                           ", EVs: " + availableEVSpots);
    }
}