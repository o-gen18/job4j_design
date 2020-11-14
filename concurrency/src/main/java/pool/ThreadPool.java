package pool;

import waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<Thread>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < cores; i++) {
            threads.add(new Thread(
                    () -> tasks.poll().run()
            ));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
