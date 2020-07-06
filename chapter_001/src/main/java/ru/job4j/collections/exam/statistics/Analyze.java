package ru.job4j.collections.exam.statistics;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {
    public Info diff(List<Analyze.User> previous, List<Analyze.User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        if (previous.size() == 0) {
            return new Info(current.size(), 0, 0);
        }

        if (current.size() == 0) {
            return new Info(0, 0, previous.size());
        }

        Set<Integer> idsPrev = previous.stream().map(User::getId).collect(Collectors.toSet());

        for (User user : current) {
            if (!idsPrev.contains(user.getId())) {
                added++;
            } else {
                User temp = previous.get(previous.indexOf(user));
                if (!user.getName().equals(temp.getName())) {
                    changed++;
                }
            }
        }
        deleted = previous.size() - (current.size() - added);
        return new Info(added, changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}
