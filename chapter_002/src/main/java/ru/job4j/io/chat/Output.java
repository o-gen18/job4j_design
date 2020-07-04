package ru.job4j.io.chat;

public interface Output {
    void loadPhraseSource();

    boolean isActivated();

    void deactivate();

    void activate();

    String generate();
}
