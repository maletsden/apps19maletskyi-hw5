package ua.edu.ucu.iterables;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterable implements Iterable<Integer> {
    private Iterable<Integer> iterable;
    private IntToIntStreamFunction func;

    public FlatMapIterable(
            Iterable<Integer> iterable, IntToIntStreamFunction func
    ) {
        this.iterable = iterable;
        this.func = func;
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> parentIterator = iterable.iterator();

        return new Iterator<Integer>() {
            Iterator<Integer> newIterator;

            public boolean hasNext() {
                if (newIterator != null && newIterator.hasNext()) {
                    return true;
                }

                while (parentIterator.hasNext()) {
                    newIterator = (
                            (AsIntStream) func.applyAsIntStream(
                                    parentIterator.next()
                            )
                    ).getIterable().iterator();

                    if (newIterator.hasNext()) {
                        return true;
                    }
                }

                return false;
            }

            public Integer next() {
                return newIterator.next();
            }
        };
    }
}
