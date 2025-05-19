package src;

import java.util.concurrent.atomic.AtomicInteger;

import Interfaces.Subscriber;

public class TopicSubscriber {
    private final Topic topic;
    private final Subscriber subscriber;
    private final AtomicInteger offset; //thread-safe
    boolean isActive = true;

    public TopicSubscriber(Topic topic, Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    public Topic getTopic() {
        return topic;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
