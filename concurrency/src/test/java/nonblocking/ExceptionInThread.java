package nonblocking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/*
Exception thrown in a thread is not visible to the main thread, and so the test passes
 */
public class ExceptionInThread {
    @Test
    public void whenThrowExceptionNotVisibleToMain() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    throw new RuntimeException("Throw Exception in the Thread");
                }
        );
        thread.start();
        thread.join();
    }

    @Test
    public void whenThrowExceptionPassedToTheMain() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in the Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Throw Exception in the Thread"));
    }
}
