package ua.edu.ucu.iterables;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntPredicate pred;

    public FilterIterable(Iterable<Integer> iterable, IntPredicate pred) {
        this.iterable = iterable;
        this.pred = pred;
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> parentIterator = iterable.iterator();

        return new Iterator<Integer>() {
            private Integer nextVal;

            public boolean hasNext() {
                while (parentIterator.hasNext()) {
                    nextVal = parentIterator.next();

                    if (pred.test(nextVal)) {
                        return true;
                    }
                }
                return false;
            }

            public Integer next() {
                return nextVal;
            }
        };
    }
}
