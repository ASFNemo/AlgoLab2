import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by asherfischbaum on 02/03/2016.
 */
public class CAIterator<E> implements Iterator<E> {

    E[] ringArray;
    int lastIndex;

    public CAIterator(E[] ringArray, int lastIndex) {
        this.ringArray = ringArray;
        this.lastIndex = lastIndex;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;

        int nextIndex = (lastIndex + 1) % ringArray.length;

        if (ringArray[nextIndex] != null){
            hasNext = true;
        }

        return hasNext;
    }

    @Override
    public E next() throws NoSuchElementException{
        int nextIndex = (lastIndex + 1) % ringArray.length;

        if (ringArray[nextIndex] == null){
            throw  new NoSuchElementException();
        }

        return ringArray[nextIndex];
    }

    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}