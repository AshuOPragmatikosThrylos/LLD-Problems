import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        MeetingRoom roomA = new MeetingRoom("Room A", 5);
        MeetingRoom roomB = new MeetingRoom("Room B", 10);

        Scheduler scheduler = new Scheduler(Arrays.asList(roomA, roomB));

        TimeSlot slot = new TimeSlot(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));

        // A weekly meeting on Tuesday and Thursday
        Set<DayOfWeek> days = new HashSet<>();
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.THURSDAY);
        RecurrenceRule recurrenceRule = new RecurrenceRule("WEEKLY", 1, days);
        Recurrence recurrence = new Recurrence(recurrenceRule);

        System.out.println("\nScheduling a recurring meeting...");
        Meeting meeting = scheduler.createMeeting("Win Loss Analysis", alice, slot, Arrays.asList(bob), recurrence);

        System.out.println("\nUpcoming Occurrences:");
        List<LocalDateTime> upcomingOccurrences = scheduler.getUpcomingOccurrences(meeting, 5);
        for (LocalDateTime occurrence : upcomingOccurrences) {
            System.out.println(" - " + occurrence);
        }

        if (!upcomingOccurrences.isEmpty()) {
            LocalDateTime occurrenceToCancel = upcomingOccurrences.get(1);
            System.out.println("\nCanceling an occurrence: " + occurrenceToCancel);
            scheduler.cancelMeetingOccurrence(meeting, occurrenceToCancel);
        }

        System.out.println("\nUpdated Upcoming Occurrences:");
        upcomingOccurrences = scheduler.getUpcomingOccurrences(meeting, 5);
        for (LocalDateTime occurrence : upcomingOccurrences) {
            System.out.println(" - " + occurrence);
        }

        System.out.println("\nCanceling the entire meeting series...");
        scheduler.cancelMeeting(meeting.getMeetingId());

        System.out.println("\nChecking if the meeting is cancelled...");
        System.out.println("Meeting: \"" + meeting.getTitle() + "\" is cancelled? "
                + meeting.getCancellationStatus());
    }
}
