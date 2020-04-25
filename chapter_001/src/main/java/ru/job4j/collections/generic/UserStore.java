package ru.job4j.collections.generic;

public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        if (store.replace(id, model)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (store.delete(id)) {
            result = true;
        }
        return result;
    }

    @Override
    public User findBy(String id) {
        return store.findBy(id);
    }
}
