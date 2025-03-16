import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private String name;
    private int capacity;
    private List<Meeting> scheduledMeetings;

    public MeetingRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.scheduledMeetings = new ArrayList<>();
    }

    public boolean isAvailable(TimeSlot timeSlot) {
        for (Meeting meeting : scheduledMeetings) {
            if (meeting.getTimeSlot().overlaps(timeSlot)) {
                return false;
            }
        }
        return true;
    }

    public void bookRoom(Meeting meeting) {
        scheduledMeetings.add(meeting);
    }

    public void cancelBooking(Meeting meeting) {
        scheduledMeetings.remove(meeting);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
