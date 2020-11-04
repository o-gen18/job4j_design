package waitnotify;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            if (count == total) {
                monitor.notifyAll();
            }
            System.out.println(++count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void await() {
        synchronized (monitor) {
            if (total != count) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(3);
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started...");
                    while (1 > 0) { //сделал так чтобы нить выходила из критической секции
                        barrier.count();
                    }
                }, "First");
        Thread second = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started...");
                }, "Second");
        Thread third = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started...");
                }, "Third");
        third.start();
        first.start();
        second.start();
    }
}
