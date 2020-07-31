package ru.job4j.collections.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SimpleMap {
    private final static class User {
        private String name;
        private int children;
        private Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return Objects.equals(name, user.name) && Objects.equals(children, user.children)
                    && Objects.equals(birthday, user.birthday);
        }
    }

    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        date.set(1990, Calendar.JANUARY, 15);
        User first = new User("Tom", 3, date);
        User second = new User("Tom", 3, date);
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
    }
}
