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
            while (queue.size() == capacity) {
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
        }
    }

    public T poll() {
        synchronized (this) {
            while (queue.isEmpty()) {
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

    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}
