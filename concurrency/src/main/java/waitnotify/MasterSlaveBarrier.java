package waitnotify;

public class MasterSlaveBarrier {
    private final Object monitor = this;

    private boolean flag = false;

    public void tryMaster() {
        synchronized (monitor) {
            System.out.println("Thread A");
            doneMaster();
        }
    }

    public void trySlave() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Thread B");
            doneSlave();
        }
    }

    public void doneMaster() {
        flag = true;
        monitor.notify();
    }

    public void doneSlave() {
        flag = false;
        monitor.notify();
    }

    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(() -> {
            while (true) {
                barrier.tryMaster();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread second = new Thread(() -> {
            while (true) {
                barrier.trySlave();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
