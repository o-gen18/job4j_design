package ru.job4j.design.srp;

public class DocFormatHTML implements DocFormat{
    @Override
    public StringBuilder formatHead(String head) {
        StringBuilder changed = new StringBuilder("<div><h1>");
        return changed.append(head).append("</h1>");
    }

    @Override
    public StringBuilder formatBody(String body) {
        StringBuilder changed = new StringBuilder("<p>");
        return changed.append(body);
    }

    @Override
    public String formatTail() {
        return "</p></div>";
    }
}
