package ua.edu.ucu.iterables;

import java.util.Iterator;

public abstract class StreamIterable implements Iterable<Integer> {
    private boolean isClosed;

    @Override
    public Iterator<Integer> iterator() {
        checkIsClosed();

        isClosed = true;

        return getIterator();
    }

    abstract Iterator<Integer> getIterator();

    private void checkIsClosed() {
        if (isClosed) {
            throw new IllegalStateException(
                    "stream has already been operated upon or closed"
            );
        }
    }
}
