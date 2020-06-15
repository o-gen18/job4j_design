package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Answer implements Output {

    private List<String> phrases = new ArrayList<>();

    private Random random = new Random();

    private String pathToSource;

    private boolean isActive;

    public Answer(String path) {
        pathToSource = path;
        this.isActive = true;
        loadPhraseSource();
    }

    public void deactivate() {
        isActive = false;
    }

    public void activate() {
        isActive = true;
    }

    public boolean isActivated() {
        return isActive;
    }

    @Override
    public void loadPhraseSource() {
        try (BufferedReader source = new BufferedReader(
                new FileReader(this.pathToSource))) {
            source.lines().forEach(phrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generate() {
        int rand = random.nextInt(phrases.size());
        return phrases.get(rand);
    }
}
