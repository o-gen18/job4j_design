package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JDKExecutors {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        pool.submit(new Runnable() { //anonymous class
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
        pool.submit(() -> System.out.println("Execute " + Thread.currentThread().getName()));
        //lambda
        pool.shutdown();
        String poolImpl = pool.getClass().getName();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
        System.out.println("Interface ExecutorService is implemented via " + poolImpl);
        System.out.println(-1 << 29);
        System.out.println(0 << 29);
        System.out.println(1 << 29);
        System.out.println(2 << 29);
    }
}
