
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerLock {

  private final Queue<Integer> buffer = new LinkedList<>();
  private final int CAPACITY = 5;


  public void produce() throws InterruptedException {
    int value = 0;
    while (true) {
      synchronized (this) {
        // Wait while the buffer is full.
        while (buffer.size() == CAPACITY) {
          System.out.println("Buffer is full. Producer is waiting...");
          wait();
        }
        // Once there is space, produce an item.
        System.out.println("Producer produced: " + value);
        buffer.offer(value++);
        // Notify all waiting threads (consumers) that a new item is available.
        notifyAll();
      }
      // Sleep for a short time to simulate production time.
      Thread.sleep(1000);
    }
  }

  public void consume() throws InterruptedException {
    while (true) {
      synchronized (this) {
        // Wait while the buffer is empty.
        while (buffer.isEmpty()) {
          System.out.println("Buffer is empty. Consumer is waiting...");
          wait();
        }
        // Once there is an item, consume it.
        int value = buffer.poll();
        System.out.println("Consumer consumed: " + value);
        // Notify all waiting threads (producers) that space is available.
        notifyAll(); // from Object class
      }
      // Sleep for a short time to simulate consumption time.
      Thread.sleep(1500);
    }
  }



  public static void main(String[] args) {
    ProducerConsumerLock pc = new ProducerConsumerLock();

    Thread producerThread = new Thread(new Runnable() {
      public void run() {
        try {
          pc.produce();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          System.err.println("Producer thread interrupted.");
        }
      }
    }, "ProducerThread");


    Thread consumerThread = new Thread(new Runnable() {
      public void run() {
        try {
          pc.consume();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          System.err.println("Consumer thread interrupted.");
        }
      }
    }, "ConsumerThread");


    producerThread.start();
    consumerThread.start();
  }
}


// Shorter producer sleep ⇒ Tests buffer full scenario
// Shorter consumer sleep ⇒ Tests buffer empty scenario