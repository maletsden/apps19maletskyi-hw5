package ua.edu.ucu.iterables;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntUnaryOperator func;

    public MapIterable(Iterable<Integer> iterable, IntUnaryOperator func) {
        this.iterable = iterable;
        this.func = func;
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> parentIterator = iterable.iterator();

        return new Iterator<Integer>() {
            public boolean hasNext() {
                return parentIterator.hasNext();
            }

            public Integer next() {
                return func.apply(parentIterator.next());
            }
        };
    }
}
