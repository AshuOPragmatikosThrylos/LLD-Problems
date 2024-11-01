package strategies;

import java.time.Duration;

import interfaces.RateStrategyInterface;
import enumerations.VehicleType;

public class HourlyRateStrategyImpl implements RateStrategyInterface {
    private double hourlyRate;
    // private VehicleType vehicleType;

    public HourlyRateStrategyImpl(double hourlyRate, VehicleType vehicleType) {
        this.hourlyRate = hourlyRate;
        // this.vehicleType = vehicleType;
    }

    public double calculateParkingCharge(Duration duration) {
        // use vehicleType for alternative logic
        long hours = duration.toHours();
        // Charge for the next hour if there's any remaining minute
        if (duration.toMinutes() % 60 != 0) {
            hours++;
        }
        return hours * hourlyRate;
    }
}