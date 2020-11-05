package waitnotify;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int capacity;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue() {
        this.capacity = 10;
    }

    public SimpleBlockingQueue(final int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        synchronized (this) {
            if (queue.size() == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(value);
            if (queue.size() == 1) {
                this.notifyAll();
            }
            System.out.println("Added value " + value);
        }
    }

    public T poll() {
        synchronized (this) {
            if (queue.size() == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            T value = queue.poll();
            if (queue.size() == capacity - 1) {
                this.notifyAll();
            }
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(
                () -> {
                    int count = 0;
                    while (count++ < 50) {
                        queue.offer(count);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        Thread consumer = new Thread(
                () -> {
                    int count = 0;
                    while (count++ < 50) {
                        System.out.println("Removed value " + queue.poll());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
    }
}
