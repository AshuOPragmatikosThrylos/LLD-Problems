package strategies;

import java.time.Duration;

import interfaces.RateStrategyInterface;
import enumerations.VehicleType;

public class FlatRateStrategyImpl implements RateStrategyInterface {
    private double flatRate;
    // private VehicleType vehicleType;

    public FlatRateStrategyImpl(double flatRate, VehicleType vehicleType) {
        this.flatRate = flatRate;
        // this.vehicleType = vehicleType;
    }

    public double calculateParkingCharge(Duration duration) {
        // use vehicleType for alternative logic
        return flatRate;
    }
}