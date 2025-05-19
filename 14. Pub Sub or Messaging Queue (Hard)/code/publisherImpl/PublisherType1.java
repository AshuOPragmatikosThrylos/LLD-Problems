package publisherImpl;

import Interfaces.Publisher;
import controllers.KafkaController;
import src.Message;

public class PublisherType1 implements Publisher {
    private final String id;
    private final KafkaController kafkaController;

    public PublisherType1(String id, KafkaController kafkaController) {
        this.id = id;
        this.kafkaController = kafkaController;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void publish(String topicId, Message message) throws IllegalArgumentException {
        kafkaController.publish(topicId, message);
        System.out.println("PublisherType1 " + id + " published: " + message.getMessage() + " to topic " + topicId);
    }
}
