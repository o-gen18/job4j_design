package ru.job4j.design.icp.menu;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MenuStartUI {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Menu menu = new Menu();
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(new CreateAction());
        actions.add(new ExtendAction());
        actions.add(new DepictAction());
        actions.add(new ExitAction());
        new MenuStartUI().init(input, menu, actions, System.out::println);
    }

    private void showMainMenu(ArrayList<Action> actions) {
        for(Action action : actions) {
            System.out.println(actions.indexOf(action) + ". " + action.name());
        }
    }

    public void init(Input input, Menu menu, ArrayList<Action> actions, Consumer<String> output) {
        boolean run = true;
        while (run) {
            this.showMainMenu(actions);
            int select = input.askInt("Выберите действие: ", actions.size());
            Action action = actions.get(select);
            run = action.execute(input, menu, output);
        }
    }
}
