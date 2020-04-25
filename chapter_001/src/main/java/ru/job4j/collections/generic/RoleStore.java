package ru.job4j.collections.generic;

public class RoleStore implements Store<Role> {
    private Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
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
    public Role findBy(String id) {
        return store.findBy(id);
    }
}
