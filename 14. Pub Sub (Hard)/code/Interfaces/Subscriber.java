package Interfaces;

import src.Message;

public interface Subscriber {
    String getId();
    void onMessage(Message message) throws InterruptedException;
}
