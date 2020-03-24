package ru.job4j.design.srp;

public class DocJson implements DocFormat {
    @Override
    public StringBuilder formatHead(String head) {
        StringBuilder changed = new StringBuilder("[ { \"Name\" : \"");
        return changed.append(head).append("\" }");
    }

    @Override
    public StringBuilder formatBody(String body) {
        StringBuilder changed = new StringBuilder("{ \"");
        return changed.append(body);
    }

    @Override
    public String formatTail() {
        return " \" } ]";
    }
}
