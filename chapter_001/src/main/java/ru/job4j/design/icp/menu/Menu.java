package ru.job4j.design.icp.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<Paragraph> list = new ArrayList<>();

    public void add(Paragraph paragraph) {
        list.add(paragraph);
    }

    public void add(int index, Paragraph paragraph) {
        list.add(index, paragraph);
    }

    public ArrayList<Paragraph> returnList() {
        return list;
    }

    public void showAll() {
        if(!(list.isEmpty())) {
            for (Paragraph paragraph : list) {
                System.out.println(list.indexOf(paragraph) + ". " +  paragraph.getName());
            }
        } else {
            System.out.println("Меню еще не создано");
        }
    }
}
