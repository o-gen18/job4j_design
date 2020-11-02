package concurrent;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    public static class User {
        private int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    @GuardedBy("this")
    private volatile Map<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        if (storage.containsKey(user.getId())) {
            return false;
        }
        storage.put(user.getId(), user);
        return true;
    }

    public synchronized boolean update(User user) {
        User previous = storage.replace(user.getId(), user);
        return previous != null;
    }

    public synchronized boolean delete(User user) {
        User removed = storage.remove(user.getId());
        return removed != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (from.getAmount() < amount || from == null || to == null || amount <= 0) {
            return false;
        }
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        return true;
    }
}
