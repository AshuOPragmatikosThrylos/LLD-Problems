package subscriberImpl;

import Interfaces.Subscriber;
import src.Message;

public class SubscriberType1 implements Subscriber {
    private final String id;
    public SubscriberType1(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        // Processing the received message
        System.out.println("SubscriberType1 " + id + " received: " + message.getMessage());
        // Simulate processing delay if desired
        Thread.sleep(500);
    }
}
