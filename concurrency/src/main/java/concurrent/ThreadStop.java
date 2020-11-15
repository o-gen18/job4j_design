package concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                System.out.println("Start...");
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                System.out.println(Thread.currentThread().isInterrupted());
                                System.out.println(Thread.currentThread().getState());
//                                Thread.currentThread().interrupt();
// Метод Thread.inturrupt()
// не выставляет флаг прерывания, если нить находиться в режиме WAIT, JOIN.
// В этом случае методы sleep, join, wait выкинуть исключение.
// Поэтому нужно дополнительно проставить флаг прерывания.
                            }
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
