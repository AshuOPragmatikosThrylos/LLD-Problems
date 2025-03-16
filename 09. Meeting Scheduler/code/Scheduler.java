import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Scheduler {
    private List<MeetingRoom> rooms;
    private Map<String, Meeting> meetings;

    public Scheduler(List<MeetingRoom> rooms) {
        this.rooms = rooms;
        this.meetings = new HashMap<>();
    }

    public Meeting createMeeting(String title, User organizer, TimeSlot timeSlot, List<User> participants,
            Recurrence recurrence) {
        Meeting meeting = new Meeting(title, organizer, timeSlot, recurrence, participants);

        if (!scheduleRoom(meeting)) {
            throw new RuntimeException("No available room for the meeting.");
        }

        for (User user : participants) {
            user.addMeeting(meeting);
        }

        meetings.put(meeting.getMeetingId(), meeting);

        sendMeetingNotification(meeting, "scheduled");

        return meeting;
    }

    public boolean cancelMeeting(String meetingId) {
        if (!meetings.containsKey(meetingId))
            return false;

        Meeting meeting = meetings.get(meetingId);
        meeting.cancelMeeting();
        meeting.getRoom().cancelBooking(meeting);

        for (User user : meeting.getParticipants()) {
            user.removeMeeting(meeting);
        }

        sendMeetingNotification(meeting, "cancelled");

        return true;
    }

    private boolean scheduleRoom(Meeting meeting) {
        int requiredCapacity = meeting.getParticipants().size();
        for (MeetingRoom room : rooms) {
            if (room.isAvailable(meeting.getTimeSlot()) && room.getCapacity() >= requiredCapacity) {
                room.bookRoom(meeting);
                meeting.setRoom(room);
                return true;
            }
        }
        return false;
    }

    private void sendMeetingNotification(Meeting meeting, String status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startTimeStr = meeting.getTimeSlot().getStartTime().format(formatter);
        long durationMinutes = Duration.between(meeting.getTimeSlot().getStartTime(),
                meeting.getTimeSlot().getEndTime()).toMinutes();

        String message = "Meeting " + status + ": " + meeting.getTitle() +
                " in " + (meeting.getRoom() != null ? meeting.getRoom().getName() : "No Room") +
                " [Start: " + startTimeStr + ", Duration: " + durationMinutes + " minutes]";

        new Notification(message, meeting.getOrganizer()).send();

        for (User participant : meeting.getParticipants()) {
            new Notification(message, participant).send();
        }
    }

    public List<LocalDateTime> getUpcomingOccurrences(Meeting meeting, int maxOccurrences) {
        return meeting.generateUpcomingOccurrences(maxOccurrences);
    }

    public boolean cancelMeetingOccurrence(Meeting meeting, LocalDateTime occurrence) {
        if (meeting.getRecurrence() == null) {
            return false; // No recurrence, canceling a single occurrence doesn't make sense
        }
    
        LocalDateTime endDate = meeting.getRecurrence().getEndDate();
        if (endDate != null && occurrence.isAfter(endDate)) {
            throw new IllegalArgumentException("Cannot cancel an occurrence beyond the recurrence end date.");
        }
    
        meeting.cancelOccurrence(occurrence);
        System.out.println("Canceled occurrence: " + occurrence);
        return true;
    }
    
}
