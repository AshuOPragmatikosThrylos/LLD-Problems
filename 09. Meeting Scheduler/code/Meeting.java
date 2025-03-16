import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Meeting {
    private String meetingId;
    private String title;
    private User organizer;
    private List<User> participants;
    private TimeSlot timeSlot;
    private Recurrence recurrence;
    private MeetingRoom room;
    private boolean isCancelled;
    private List<LocalDateTime> cancelledOccurrences;

    public Meeting(String title, User organizer, TimeSlot timeSlot, Recurrence recurrence, List<User> participants) {
        this.meetingId = UUID.randomUUID().toString();
        this.title = title;
        this.organizer = organizer;
        this.timeSlot = timeSlot;
        this.recurrence = recurrence;
        this.participants = participants;
        this.isCancelled = false;
        this.cancelledOccurrences = new ArrayList<>();
    }

    public boolean isConflict(Meeting other) {
        if (this.recurrence.isLifetime() || other.recurrence.isLifetime()) {
            return true;
        }
        return this.timeSlot.overlaps(other.timeSlot);
    }

    public void cancelMeeting() {
        this.isCancelled = true;
    }

    public boolean getCancellationStatus() {
        return this.isCancelled;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getTitle() {
        return title;
    }

    public User getOrganizer() {
        return organizer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public void cancelOccurrence(LocalDateTime occurrence) {
        cancelledOccurrences.add(occurrence);
    }

    public List<LocalDateTime> generateUpcomingOccurrences(int maxOccurrences) {
        List<LocalDateTime> occurrences = new ArrayList<>();
        LocalDateTime nextOccurrence = timeSlot.getStartTime();

        while (occurrences.size() < maxOccurrences) {
            if (!cancelledOccurrences.contains(nextOccurrence)) {
                occurrences.add(nextOccurrence);
            }
            nextOccurrence = recurrence.getNextOccurrence(nextOccurrence);
            if (recurrence.getEndDate() != null && nextOccurrence.isAfter(recurrence.getEndDate())) {
                break;
            }
        }

        return occurrences;
    }
}
