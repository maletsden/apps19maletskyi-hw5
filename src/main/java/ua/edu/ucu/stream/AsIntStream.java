package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterables.FilterIterable;
import ua.edu.ucu.iterables.FlatMapIterable;
import ua.edu.ucu.iterables.MapIterable;
import ua.edu.ucu.iterables.OfIterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class AsIntStream implements IntStream {
    private Iterable<Integer> iterable;

    private AsIntStream(Iterable<Integer> iterable) {
        this.iterable = iterable;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new OfIterable(values));
    }

    @Override
    public Double average() {
        int result = 0, counter = 0;

        for (Integer number : iterable) {
            result += number;
            counter += 1;
        }

        return ((double) result) / counter;
    }

    @Override
    public Integer max() {
        return getMinMax(false);
    }

    @Override
    public Integer min() {
        return getMinMax(true);
    }

    private Integer getMinMax(boolean min) {
        Iterator<Integer> iterator = iterable.iterator();

        if (!iterator.hasNext()) {
            return null;
        }

        Integer value = iterator.next();

        while (iterator.hasNext()) {
            Integer nextVal = iterator.next();

            if (nextVal < value == min) {
                value = nextVal;
            }
        }
        return value;
    }

    @Override
    public long count() {
        long counter = 0;

        for (Integer number : iterable) {
            counter += 1;
        }

        return counter;
    }

    @Override
    public Integer sum() {
        Integer result = 0;

        for (Integer number : iterable) {
            result += number;
        }

        return result;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterable(iterable, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Integer number : iterable) {
            action.accept(number);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterable(iterable, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterable(iterable, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (Integer number : iterable) {
            result = op.apply(result, number);
        }

        return result;
    }

    @Override
    public int[] toArray() {
        int[] array = new int[(int) count()];

        int i = 0;

        for (Integer number : iterable) {
            array[i++] = number;
        }

        return array;
    }

    public Iterable<Integer> getIterable() {
        return iterable;
    }
}
