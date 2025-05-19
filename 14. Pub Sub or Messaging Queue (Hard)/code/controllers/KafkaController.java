package controllers;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

import src.Topic;
import src.TopicSubscriber;
import Interfaces.Subscriber;
import src.Message;

// Broker
public class KafkaController {
    // Map of topic IDs to Topic objects
    private final Map<String, Topic> topics;
    // Map of topic IDs to their list of TopicSubscriber associations
    private final Map<String, List<TopicSubscriber>> topicSubscribers;
    // ExecutorService to run subscriber tasks concurrently
    private final ExecutorService subscriberExecutor;
    private final AtomicInteger topicIdCounter;

    public KafkaController() {
        topics = new ConcurrentHashMap<>();
        topicSubscribers = new ConcurrentHashMap<>();
        // Using a cached thread pool to dynamically manage threads.
        subscriberExecutor = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName) {
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Created topic: " + topicName + " with id: " + topicId);
        return topic;
    }

    public void subscribe(Subscriber subscriber, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }
        TopicSubscriber ts = new TopicSubscriber(topic, subscriber);
        topicSubscribers.get(topicId).add(ts);
        // Submit the subscriber task to the executor.
        subscriberExecutor.submit(new TopicSubscriberController(ts));
        System.out.println("Subscriber " + subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void unsubscribe(Subscriber subscriber, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }

        List<TopicSubscriber> tsList = topicSubscribers.get(topicId);
        if (tsList == null || tsList.isEmpty()) {
            System.err.println("No subscribers found for topic " + topicId);
            return;
        }

        boolean removed = tsList.removeIf(ts -> {
            if (ts.getSubscriber().getId().equals(subscriber.getId())) {
                ts.setActive(false);
                return true;
            }
            return false;
        });
        
        if (removed) {
            System.out.println("Subscriber " + subscriber.getId() + " unsubscribed from topic: " + topic.getTopicName());
        } else {
            System.err.println("Subscriber " + subscriber.getId() + " not found in topic: " + topic.getTopicName());
        }
    }

    public void publish(String topicId, Message message) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic with id " + topicId + " does not exist");
        }
        topic.addMessage(message);
        // wake up each subscriber on its own monitor
        List<TopicSubscriber> subs = topicSubscribers.get(topicId);
        for (TopicSubscriber topicSubscriber : subs) {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
        System.out.println("Message \"" + message.getMessage() + "\" published to topic: " + topic.getTopicName());
    }

    // Resets the offset for the given subscriber on the specified topic
    public void resetOffset(String topicId, Subscriber subscriber, int newOffset) {
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if (subscribers == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }
        for (TopicSubscriber ts : subscribers) {
            if (ts.getSubscriber().getId().equals(subscriber.getId())) {
                ts.getOffset().set(newOffset);
                // Notify in case the subscriber thread is waiting
                synchronized (ts) {
                    ts.notify();
                }
                System.out.println("Offset for subscriber " + subscriber.getId() + " on topic " +
                        ts.getTopic().getTopicName() + " reset to " + newOffset);
                break;
            }
        }
    }

    // Shutdown the ExecutorService gracefully
    public void shutdown() {
        subscriberExecutor.shutdown(); // tells the executor to stop accepting new tasks, but lets existing tasks complete
        try {
            if (!subscriberExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                subscriberExecutor.shutdownNow(); // forcefully shutdown if tasks are not completed in 5 seconds
            }
        } catch (InterruptedException e) {
            subscriberExecutor.shutdownNow();
        }
    }
}
