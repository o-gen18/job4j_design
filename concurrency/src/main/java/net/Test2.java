package net;

import concurrent.ConsoleProgress;

import java.io.InputStream;
import java.util.function.Consumer;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        ConsoleProgress cp = new ConsoleProgress();
        Runnable run = count::sayHello;
        Consumer<String> con = System.out::println;
        Thread first = new Thread(run);
        Thread second = new Thread(count::increment);
        //интерфейс Runnable можно проинициализировать ссылкой на метод
        Thread fourth = new Thread(count::increment);
        Thread third = new Thread(count::increment);
        Thread fifth = new Thread(count::increment);
        Thread sixth = new Thread(() -> {
            count.increment(); //explicit depicting
        });
        third.start();
        fourth.start();
        first.start();
        fifth.start();
        sixth.start();
        second.start();
        first.join();
        second.join();
        System.out.println(count.get());
        con.accept("Consumer says");
    }
}
