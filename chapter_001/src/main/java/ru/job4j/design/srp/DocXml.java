package ru.job4j.design.srp;

public class DocXml implements DocFormat {
    @Override
    public StringBuilder formatHead(String head) {
        StringBuilder changed = new StringBuilder(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><name>");
        changed.append(head);
        return changed.append("</name>");
    }

    @Override
    public StringBuilder formatBody(String body) {
        StringBuilder changed = new StringBuilder("<lines>");
        return changed.append(body).append("</lines>");
    }

    @Override
    public String formatTail() {
        return "</document>";
    }
}
