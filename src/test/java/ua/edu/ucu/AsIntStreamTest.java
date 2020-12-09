package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream intStream;
    private IntStream empty;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        int[] emptyArray = {};
        intStream = AsIntStream.of(intArr);
        empty = AsIntStream.of(emptyArray);
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.001);
    }

    @Test
    public void testToArray() {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = intStream.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        int result = empty.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        int result = empty.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        double result = empty.average();
    }

    @Test
    public void testCount() {
        int expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testCountEmpty() {
        long result = empty.count();
        assertEquals(0, result);
    }

    @Test
    public void testSum() {
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        int result = empty.sum();
    }

    @Test
    public void testFilter() {
        int[] expResult = {0, 1, 2};
        int[] result = intStream.filter(x -> x >= 0).filter(x -> x < 3).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFilterEmpty() {
        int[] expResult = {};
        int[] result = empty.filter(x -> x > 0).filter(x -> x < 3).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMap() {
        int[] expResult = {2, 1, 2, 5, 10};
        int[] result = intStream.map(x -> x * x).map(x -> x + 1).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMapEmpty() {
        int[] expResult = {};
        int[] result = empty.map(x -> x * x).map(x -> x + 1).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = {1, 3, 19};
        int[] result = intStream.filter(x -> x > 2)
                .flatMap(x -> AsIntStream.of(x - 2, x, x + 16)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMapEmpty() {
        int[] expResult = {};
        int[] result = empty.flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        int expResult = 15 + 5;
        int result = intStream.reduce(15, (sum, x) -> sum += x);
        assertEquals(expResult, result);
    }

    @Test
    public void testReduceEmpty() {
        int result = empty.reduce(15, (sum, x) -> sum += x);
        assertEquals(15, result);
    }
    @Test
    public void testForEach() {
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        assertEquals("-10123", str.toString());
    }
    @Test
    public void testConstructor() {
        IntStream stream = AsIntStream.of();
        long len = stream.count();
        assertEquals(len, 0);
    }


}
