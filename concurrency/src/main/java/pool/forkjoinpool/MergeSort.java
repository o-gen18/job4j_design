package pool.forkjoinpool;

import java.util.Arrays;

public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        if (from == to) { //если массив из одного элемента, вернуть его
            return new int[] {array[from] };
        }
        //если в массиве более одного элемента, найти середину
        int mid = (from + to) / 2;
        //объединить отсортированные части
        return merge(
                sort(array, from, mid), //сортировка левой части
                sort(array, mid + 1, to) //сортировка правой части
        );
    }

    //метод объединения двух отсортированных массивов
    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] < right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {6, 3, 1, 5, 4, 2};
        System.out.println(Arrays.toString(sort(array)));
    }
}
