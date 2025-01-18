package interfaces;
import java.time.Duration;

public interface RateStrategyInterface {
    double calculateParkingCharge(Duration duration);
}
