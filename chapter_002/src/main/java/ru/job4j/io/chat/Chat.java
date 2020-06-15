package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Chat {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Введите аргументы для метода main():"
                            + "1-путь к файлу чтения, 2-путь к файлу записи");
        }
        Input input = new ConsoleInput();
        Output output = new Answer(args[0]);
        Saver log = new Logger(args[1]);
        String phrase;
        System.out.println("Введите что-нибудь");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            do {
                phrase = input.takeInput(reader);
                log.collect(phrase);
                if (phrase.equals("стоп") || phrase.equals("закончить")) {
                    output.deactivate();
                }
                if (phrase.equals("продолжить")) {
                    output.activate();
                }
                if (output.isActivated()) {
                    String answer = output.generate();
                    System.out.println(answer);
                    log.collect(answer);
                }
            } while (!phrase.equals("закончить"));
            log.writeToLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
