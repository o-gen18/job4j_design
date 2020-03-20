package ru.job4j.design.icp.menu;

public class Paragraph {
    /**
     * This field indicates how many subParagraphs this particular
     * paragraph has. Each time a user adds new subParagraph to
     * this one, the field iterates.
     */
    private int subParagraphs;

    /**
     * Prefix is used to visualize subParagraphs so that
     * they are distinguishable on the screen.
     */
    private String prefix = "";
    private String name;

    public Paragraph() {
    }

    public Paragraph(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        setName(prefix + name);
    }

    public String getPrefix() {
        return prefix;
    }

    public int getSubParagraphs() {
        return subParagraphs;
    }

    public void iterateSubParagraphs() {
        subParagraphs++;
    }
}
