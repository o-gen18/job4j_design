package asynch;

import org.junit.Test;
import org.junit.Before;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RowColSumTest {
    private int[][] matrix;
    private RowColSum.Sums[] sequential;
    private RowColSum.Sums[] asynchronous;

    @Before
    public void init() throws ExecutionException, InterruptedException {
        matrix = new int[][]
                {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        sequential = RowColSum.sum(matrix);
        asynchronous = RowColSum.asyncSum(matrix);
    }

    @Test
    public void whenSequentialSumThenCorrectResult() {
        assertThat(sequential[0].getColSum(), is(55));
        assertThat(sequential[0].getRowSum(), is(15));

        assertThat(sequential[1].getColSum(), is(60));
        assertThat(sequential[1].getRowSum(), is(40));

        assertThat(sequential[2].getColSum(), is(65));
        assertThat(sequential[2].getRowSum(), is(65));

        assertThat(sequential[3].getColSum(), is(70));
        assertThat(sequential[3].getRowSum(), is(90));

        assertThat(sequential[4].getColSum(), is(75));
        assertThat(sequential[4].getRowSum(), is(115));
    }

    @Test
    public void whenAsyncSumThenCorrectResult() {
        assertThat(asynchronous[0].getColSum(), is(55));
        assertThat(asynchronous[0].getRowSum(), is(15));

        assertThat(asynchronous[1].getColSum(), is(60));
        assertThat(asynchronous[1].getRowSum(), is(40));

        assertThat(asynchronous[2].getColSum(), is(65));
        assertThat(asynchronous[2].getRowSum(), is(65));

        assertThat(asynchronous[3].getColSum(), is(70));
        assertThat(asynchronous[3].getRowSum(), is(90));

        assertThat(asynchronous[4].getColSum(), is(75));
        assertThat(asynchronous[4].getRowSum(), is(115));
    }
}
