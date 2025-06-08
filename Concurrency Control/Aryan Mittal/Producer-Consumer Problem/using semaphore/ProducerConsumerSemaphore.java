import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore {

  private final Queue<Integer> buffer = new LinkedList<>();
  private final int CAPACITY = 5;

  private final Semaphore emptySlots = new Semaphore(CAPACITY);
  private final Semaphore filledSlots = new Semaphore(0);
  private final Object mutex = new Object();

  public void produce() throws InterruptedException {
    int value = 0;
    while (true) {
      emptySlots.acquire(); // wait for an empty slot

      synchronized (mutex) {
        System.out.println("Producer produced: " + value);
        buffer.offer(value++);
      }

      filledSlots.release(); // signal that a new item is available
      Thread.sleep(1000);    // simulate production time
    }
  }

  public void consume() throws InterruptedException {
    while (true) {
      filledSlots.acquire(); // wait for an item

      int value;
      synchronized (mutex) {
        value = buffer.poll();
        System.out.println("Consumer consumed: " + value);
      }

      emptySlots.release(); // signal that space is available
      Thread.sleep(1500);   // simulate consumption time
    }
  }

  public static void main(String[] args) {
    ProducerConsumerSemaphore pc = new ProducerConsumerSemaphore();

    Thread producerThread = new Thread(() -> {
      try {
        pc.produce();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Producer thread interrupted.");
      }
    }, "ProducerThread");

    Thread consumerThread = new Thread(() -> {
      try {
        pc.consume();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Consumer thread interrupted.");
      }
    }, "ConsumerThread");

    producerThread.start();
    consumerThread.start();
  }
}

// Shorter producer sleep ⇒ Tests buffer full scenario
// Shorter consumer sleep ⇒ Tests buffer empty scenario
