import controllers.KafkaController;
import src.Topic;
import subscriberImpl.SubscriberType1;
import publisherImpl.PublisherType1;
import src.Message;

public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController = new KafkaController();

        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        SubscriberType1 subscriber1 = new SubscriberType1("Subscriber1");
        SubscriberType1 subscriber2 = new SubscriberType1("Subscriber2");
        SubscriberType1 subscriber3 = new SubscriberType1("Subscriber3");

        kafkaController.subscribe(subscriber1, topic1.getTopicId());
        kafkaController.subscribe(subscriber1, topic2.getTopicId());
        kafkaController.subscribe(subscriber2, topic1.getTopicId());
        kafkaController.subscribe(subscriber3, topic2.getTopicId());

        PublisherType1 publisher1 = new PublisherType1("Publisher1", kafkaController);
        PublisherType1 publisher2 = new PublisherType1("Publisher2", kafkaController);

        publisher1.publish(topic1.getTopicId(), new Message("Message m1"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m2"));
        publisher2.publish(topic2.getTopicId(), new Message("Message m3"));

        // Allow time for subscribers to process messages
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        kafkaController.unsubscribe(subscriber1, topic1.getTopicId());

        publisher2.publish(topic2.getTopicId(), new Message("Message m4"));
        publisher1.publish(topic1.getTopicId(), new Message("Message m5"));

        kafkaController.resetOffset(topic1.getTopicId(), subscriber1, 0);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaController.shutdown();
    }
}