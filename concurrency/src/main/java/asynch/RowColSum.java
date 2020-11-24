package asynch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            int colSum = 0;
            int rowSum = 0;
            for (int col = 0; col < matrix.length; col++) {
                colSum += matrix[col][row];
                rowSum += matrix[row][col];
            }
            sums[row] = new Sums();
            sums[row].setColSum(colSum);
            sums[row].setRowSum(rowSum);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        Sums[] sums = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            futures.put(row, getTask(matrix, row));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> {
            int colSum = 0;
            int rowSum = 0;
            for (int col = 0; col < matrix.length; col++) {
                colSum += matrix[col][row];
                rowSum += matrix[row][col];
            }
            Sums sum = new Sums();
            sum.setColSum(colSum);
            sum.setRowSum(rowSum);
            return sum;
        });
    }
}
