package pool.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<java.lang.Integer> {

    private final Object[] array;
    private final Object object;
    private int from;
    private int to;

    public ParallelIndexSearch(Object[] array, Object object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private int linear(Object object) {
        int index = from;
        for (Object ob : array) {
            if (ob.equals(object)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linear(object);
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch leftHalf = new ParallelIndexSearch(array, object, from, mid);
        ParallelIndexSearch rightHalf = new ParallelIndexSearch(array, object, mid + 1, to);
        leftHalf.fork();
        rightHalf.fork();
        int left = leftHalf.join();
        int right = rightHalf.join();
        return left == -1 ? right : left;
    }

    public static int findIndex(Object[] array, Object object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(
                new ParallelIndexSearch(array, object, 0, array.length - 1));
    }
}
