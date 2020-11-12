package nonblocking.nonblockingcache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NonBlockingCacheTest {
    @Test
    public void whenVersionsDifferThenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockingCache cache = new NonBlockingCache();
        Base model = new Base(1, "First");
        Base modelUpdated = new Base(1, 2, "Second");
        cache.add(model);
        Thread thread = new Thread(
                () -> {
                    try {
                        cache.update(modelUpdated);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Versions of models differ"));
    }

    @Test
    public void when3ThreadsAdd3ModelsThenCacheSizeIs9() throws InterruptedException {
        NonBlockingCache cache = new NonBlockingCache();
        Thread first = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        cache.add(new Base(i, "Added By first Thread"));
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        cache.add(new Base(i, "Added By second Thread"));
                    }
                }
        );
        Thread third = new Thread(
                () -> {
                    for (int i = 3; i > 0; i--) {
                        cache.add(new Base(i, "Added By third Thread"));
                    }
                }
        );
        second.start();
        first.start();
        third.start();
        second.join();
        first.join();
        third.join();
        assertThat(cache.size(), is(9));
    }

    @Test
    public void whenChangeNameOfModelThenIncrementVersion() {
        NonBlockingCache cache = new NonBlockingCache();
        Base model = new Base(0, "Zero Version");
        Base modelUpdated = new Base(1, 0, "Updated Version");
        cache.add(model);
        cache.update(modelUpdated);
        assertThat(cache.get(1).getVersion(), is(1));
        assertThat(cache.get(1).getName(), is("Updated Version"));
    }
}
