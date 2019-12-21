package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.*;

public class AsIntStreamTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamOf() {
        System.out.println("testStreamOf");
        int[] expResult = {-1, 0, 1, 2, 3};
        assertArrayEquals(expResult, intStream.toArray());
    }

    @Test
    public void testStreamFilter() {
        System.out.println("testStreamFilter");
        int[] expResult = {1, 2, 3};
        intStream = intStream.filter(x -> x > 0);
        assertArrayEquals(expResult, intStream.toArray());
    }

    @Test
    public void testStreamMap() {
        System.out.println("testStreamMap");
        int[] expResult = {1, 0, 1, 4, 9};
        intStream = intStream.map(x -> x * x);
        assertArrayEquals(expResult, intStream.toArray());
    }

    @Test
    public void testStreamFlatMap() {
        System.out.println("testStreamFlatMap");
        int[] expResult = {-1, -1, 0, 0, 1, 1, 2, 2, 3, 3};
        intStream = intStream.flatMap(x -> AsIntStream.of(x, x));
        assertArrayEquals(expResult, intStream.toArray());
    }

    @Test
    public void testStreamSum() {
        System.out.println("testStreamSum");
        Integer expResult = 5;
        assertEquals(expResult, intStream.sum());
    }

    @Test
    public void testStreamAverage() {
        System.out.println("testStreamAverage");
        double expResult = 1.0;
        assertEquals(expResult, intStream.average(), 0.00001);
    }

    @Test
    public void testStreamReduce() {
        System.out.println("testStreamReduce");
        int expResult = 5;
        assertEquals(expResult, intStream.reduce(0, (idelity, x) -> idelity += x));
    }

    @Test
    public void testStreamMin() {
        System.out.println("testStreamMin");
        Integer expResult = -1;
        assertEquals(expResult, intStream.min());
    }

    @Test
    public void testStreamMax() {
        System.out.println("testStreamMax");
        Integer expResult = 3;
        assertEquals(expResult, intStream.max());
    }

    @Test
    public void testStreamMaxOfEmptyStream() {
        System.out.println("testStreamMaxOfEmptyStream");
        intStream = intStream.filter(x -> x > 10);
        assertNull(intStream.max());
    }

    @Test
    public void testStreamCount() {
        System.out.println("testStreamCount");
        long expResult = 5;
        assertEquals(expResult, intStream.count());
    }

    @Test(expected = IllegalStateException.class)
    public void testStreamClosedError() {
        System.out.println("testStreamClosedError");
        intStream.toArray();
        intStream.toArray();
    }

}
