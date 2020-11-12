package nonblocking.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingCache {
    private final AtomicInteger uniqueId = new AtomicInteger(0);
    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.put(uniqueId.incrementAndGet(),
                Base.copyOf(model, uniqueId.get())
        );
    }

    public void update(Base model) {
        cache.computeIfPresent(model.getId(),
                (id, baseUpdating) -> {
                    if (model.getVersion() != baseUpdating.getVersion()) {
                        throw new OptimisticException("Versions of models differ");
                    }
                    baseUpdating.setName(model.getName());
                    baseUpdating.setVersion(model.getVersion() + 1);
                    return baseUpdating;
                }
        );
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public int size() {
        return cache.size();
    }

    public Base get(int id) {
        return cache.get(id);
    }
}
