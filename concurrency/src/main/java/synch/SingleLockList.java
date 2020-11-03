package synch;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;
import ru.job4j.collections.list.SimpleArray;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private SimpleArray<T> list = new SimpleArray<>();

    private SimpleArray<T> copy(SimpleArray<T> array) {
        SimpleArray<T> newCopy = new SimpleArray<>();
        int index = 0;
        while (true) {
            try {
                newCopy.add(array.get(index++));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return newCopy;
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
