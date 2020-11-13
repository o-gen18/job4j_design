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

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        Runnable job = () -> {
            int i = 0;
            while (i < 100) {
                System.out.println(i);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        };
        System.out.println("Before Job");
        pool.work(job);
//        for (int i = 4; i > 0; i--) {
//            pool.work(job);
//        }
        Thread.sleep(10000);
        pool.shutdown();
    }
}
