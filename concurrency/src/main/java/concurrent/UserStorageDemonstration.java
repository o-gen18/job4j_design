package concurrent;

public class UserStorageDemonstration {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new UserStorage.User(1, 100));
        storage.add(new UserStorage.User(2, 200));
        storage.transfer(1, 2, 50);

    }
}
