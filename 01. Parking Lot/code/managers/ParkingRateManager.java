package managers;

import java.time.Duration;

import interfaces.RateStrategyInterface;

public class ParkingRateManager {
    private RateStrategyInterface rateStrategy;

    public ParkingRateManager(RateStrategyInterface rateStrategy) {
        this.rateStrategy = rateStrategy;
    }

    public double calculateParkingCharge(Duration duration) {
        return rateStrategy.calculateParkingCharge(duration);
    }
}
