import java.util.concurrent.*;

public class SemaphoreBarrierExecutorDemo {
  // A reusable barrier implemented with semaphores
  static class SemaphoreBarrier {
    private final int numThreads;
    private int count;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore barrier = new Semaphore(0);
    
    public SemaphoreBarrier(int numThreads) {
      this.numThreads = numThreads;
      this.count = numThreads;
    }

    public void await() throws InterruptedException {
      mutex.acquire();
      count--;
      if (count == 0) {
        // Last thread arrives: release all waiting threads
        barrier.release(numThreads - 1);
        // Reset barrier state for reuse
        count = numThreads;
        mutex.release();
      } else {
        // Release mutex so other threads can update the count
        mutex.release();
        // Wait until the last thread releases this thread
        barrier.acquire();
      }
    }
  }

  public static void main(String[] args) {
    final int numThreads = 5;
    final SemaphoreBarrier barrier = new SemaphoreBarrier(numThreads);
    // Create a fixed thread pool with custom thread names
    ExecutorService executor = Executors.newFixedThreadPool(numThreads, new ThreadFactory() {
      private int counter = 1;
      @Override
      public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "Worker-" + counter);
        counter++;
        return t;
      }
    });
    // Submit tasks to the executor
    for (int i = 0; i < numThreads; i++) {
      executor.submit(() -> {
        try {
          // Phase 1: Some work before reaching the first barrier
          System.out.println(Thread.currentThread().getName() + " doing phase 1 work");
          Thread.sleep((long) (Math.random() * 1000)); // Simulate work
          System.out.println(
              Thread.currentThread().getName() + " arrived at barrier after phase 1");
          barrier.await(); // Wait until all threads reach here

          // Phase 2: This phase begins only after every thread has finished phase 1
          System.out.println(Thread.currentThread().getName() + " starting phase 2");
          Thread.sleep((long) (Math.random() * 1000)); // Simulate work
          System.out.println(Thread.currentThread().getName() + " finished phase 2");
          barrier.await(); // Synchronize end of phase 2
          
          // Phase 3: The final phase starts after all threads have completed phase 2
          System.out.println(Thread.currentThread().getName() + " starting phase 3");
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          System.out.println(Thread.currentThread().getName() + " was interrupted");
        }
      });
    }
    // Initiate an orderly shutdown
    executor.shutdown();
    try {
      if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
        System.out.println("Some tasks did not finish in time");
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted");
      executor.shutdownNow();
    }
    System.out.println("All tasks completed");
  }
}