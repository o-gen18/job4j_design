package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public static class User {
        private String username;
        private String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public void emailTo(User user) {
        String subject = "Notification " + user.getUsername()
                + " to email " + user.getEmail();
        String body = "Add new event to " + user.getUsername();
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
    }
}
