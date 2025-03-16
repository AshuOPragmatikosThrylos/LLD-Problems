import java.time.DayOfWeek;
import java.util.Set;

public class RecurrenceRule {
    // Frequency can be "DAILY", "WEEKLY", "MONTHLY", "QUARTERLY", "ANNUAL",
    // "LIFETIME" etc
    private String frequency;
    // Interval between occurrences (e.g., every 2 days, every 3 weeks)
    private int interval;
    // For weekly recurrence, an optional set of days (e.g., Tuesday, Thursday)
    private Set<DayOfWeek> daysOfWeek;

    public RecurrenceRule(String frequency, int interval, Set<DayOfWeek> daysOfWeek) {
        this.frequency = frequency;
        this.interval = interval;
        this.daysOfWeek = daysOfWeek;
    }

    // Constructor for rules that do not require specific days (e.g., daily or
    // monthly)
    public RecurrenceRule(String frequency, int interval) {
        this(frequency, interval, null);
    }

    public String getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }
}
