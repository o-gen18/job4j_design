package nonblocking;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CASCountTest {

    @Test
    public void when3ThreadsIncrementBy3Then9() throws InterruptedException {
        final CASCount<Integer> counter = new CASCount<>();
        counter.set(0);
        Thread first = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        counter.increment();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        counter.increment();
                    }
                }
        );
        Thread third = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        counter.increment();
                    }
                }
        );
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        assertThat(counter.get(), is(9));
    }
}
