package Interfaces;

import src.Message;

public interface Publisher {
    String getId();
    void publish(String topicId, Message message) throws IllegalArgumentException;
}
