package ua.edu.ucu.iterables;

import java.util.Iterator;

public class OfIterable implements Iterable<Integer> {
    private int[] values;

    public OfIterable(int[] values) {
        this.values = values;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;

            public boolean hasNext() {
                return values.length > index;
            }

            public Integer next() {
                return values[index++];
            }
        };
    }
}
