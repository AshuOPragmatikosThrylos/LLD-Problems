import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Meeting> meetings;

    public User(String name) {
        this.name = name;
        this.meetings = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public String getName() {
        return name;
    }
}
