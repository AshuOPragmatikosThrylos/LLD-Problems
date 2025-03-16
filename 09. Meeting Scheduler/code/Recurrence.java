import java.time.LocalDateTime;

public class Recurrence {
    private RecurrenceRule rule;
    private LocalDateTime endDate;


    public Recurrence(RecurrenceRule rule) {
        this.rule = rule;
    }

    public LocalDateTime getNextOccurrence(LocalDateTime currentOccurrence) {
        String frequency = rule.getFrequency();
        int interval = rule.getInterval();

        if ("LIFETIME".equalsIgnoreCase(frequency)) {
            return currentOccurrence; // since meeting is always active
        } else if ("DAILY".equalsIgnoreCase(frequency)) {
            return currentOccurrence.plusDays(interval);
        } else if ("WEEKLY".equalsIgnoreCase(frequency)) {
            if (rule.getDaysOfWeek() == null || rule.getDaysOfWeek().isEmpty()) {
                return currentOccurrence.plusWeeks(interval);
            } else {
                LocalDateTime nextOccurrence = currentOccurrence.plusDays(1);
                while (!rule.getDaysOfWeek().contains(nextOccurrence.getDayOfWeek())) {
                    nextOccurrence = nextOccurrence.plusDays(1);
                }
                return nextOccurrence;
            }
        } else if ("MONTHLY".equalsIgnoreCase(frequency)) {
            return currentOccurrence.plusMonths(interval);
        } else if ("ANNUAL".equalsIgnoreCase(frequency)) {
            return currentOccurrence.plusYears(interval);
        } else {
            throw new UnsupportedOperationException("Frequency '" + frequency + "' is not supported.");
        }
    }

    public boolean isLifetime() {
        return "LIFETIME".equalsIgnoreCase(rule.getFrequency());
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
