package ru.job4j.design.composite;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuTest {
    @Test
    public void whenMenuHas2Paragraphs2SubParagraphs2SubSubParagraphs() {
        MenuComponent par1 = new ParagraphComposite("Задача 1.");
        MenuComponent subPar11 = new SubParagraphLeaf("Задача 1.1");
        MenuComponent subSubPar111 = new SubSubParagraph("Задача 1.1.1");
        MenuComponent subSubPar112 = new SubSubParagraph("Задача 1.1.2");
        subPar11.add(subSubPar111);
        subPar11.add(subSubPar112);

        MenuComponent subPar12 = new SubParagraphLeaf("Задача 1.2");
        MenuComponent subSubPar121 = new SubSubParagraph("Задача 1.2.1");
        MenuComponent subSubPar122 = new SubSubParagraph("Задача 1.2.2");
        subPar12.add(subSubPar121);
        subPar12.add(subSubPar122);
        par1.add(subPar11);
        par1.add(subPar12);

        MenuComponent par2 = new ParagraphComposite("Задача 2.");
        MenuComponent subPar21 = new SubParagraphLeaf("Задача 2.1");
        MenuComponent subSubPar221 = new SubSubParagraph("Задача 2.1.1");
        par2.add(subPar21);
        subPar21.add(subSubPar221);
        String result = par1.toString() + par2.toString();
        String expected = "Задача 1." + System.lineSeparator()
                + "--- Задача 1.1" + System.lineSeparator()
                + "------ Задача 1.1.1" + System.lineSeparator()
                + "------ Задача 1.1.2" + System.lineSeparator()
                + "--- Задача 1.2" + System.lineSeparator()
                + "------ Задача 1.2.1" + System.lineSeparator()
                + "------ Задача 1.2.2" + System.lineSeparator()
                + "Задача 2." + System.lineSeparator()
                + "--- Задача 2.1" + System.lineSeparator()
                + "------ Задача 2.1.1" + System.lineSeparator();
        assertThat(result, is(expected));
    }
}
