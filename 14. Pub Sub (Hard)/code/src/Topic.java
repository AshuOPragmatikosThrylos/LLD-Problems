package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

    private final String topicName;
    private final String topicId;

    // This list is exposed to the outside using an immutable getter
    private final List<Message> messages;

    public Topic(final String topicName, final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public synchronized void addMessage(Message message) { // cuz critical section
        messages.add(message);
    }

    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
