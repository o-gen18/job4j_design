package concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = {'\\', '|', '/', '-', '\\', '|', '/', '-'};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading..." + process[i++]);
            i = i > 7 ? 0 : i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
