import java.sql.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E> {

    int tailPos = 0;
    int total = 0;
    int size = 0;

    E[] ringArray;

    public CircularArrayRing(){
        total = 10;
        ringArray = (E[]) new Object[10];
    }
    public CircularArrayRing(int total){
        System.out.println("HHHHH NEW TEST HHHHH");
        this.total = total;
        ringArray = (E[]) new Object[total];

    }
    public E get(int n) throws IndexOutOfBoundsException{ // returns n'th number in array
        if ((size < n) || ( ringArray.length <= n)){
            throw new IndexOutOfBoundsException();
        } else {
            int first = tailPos - 1;
            if (first < 0){first = first + total;};
            int actualN; //used to get the n'th number in array
            if(n > first){
                actualN = first - n + total;
            }
            else{
                actualN = first - n;
            }
            System.out.println("ActualN:" + actualN);
            System.out.println("GEtting:" + ringArray[actualN]);
            return ringArray[actualN];// index 0 is the most recently added element of the array, working backwards
        }
    }

    @SuppressWarnings("unchecked")
    public Iterator<E> iterator(){
        @SuppressWarnings({ "unchecked", "rawtypes" })
        Iterator it = new CAIterator(ringArray, tailPos);
        return it;
    }



    public boolean add(E e){

        ringArray[tailPos] =  e; // ERROR HERE

        if(tailPos == total - 1){
            tailPos = 0;
        }
        else{
            tailPos++;
        }

        if(size < total){
            size++;
        }
        System.out.println("Added:" + e);
        System.out.println("Num of Elements:" + size);
        System.out.println("Total:" + total);
        return true;
    }
    @Override
    public int size() {
        return size;
    }
    public int getTailPos() {
        return tailPos;
    }
    public int getTotal() {
        return total;
    }
    public E[] getArray(){
        return ringArray;
    }

    class CAItertor<E> implements Iterator<E> {

        E[] ringArray;
        int lastIndex;
        int moveAmount;

        public CAItertor(int elementNo) {
            this.ringArray = (E[]) CircularArrayRing.this.getArray();
            this.lastIndex = tailPos;
            this.moveAmount = elementNo;
        }

        public CAItertor() {
            this.ringArray = (E[]) CircularArrayRing.this.getArray();
            this.lastIndex = tailPos;
            this.moveAmount = 0;
        }

        @Override
        public boolean hasNext() {
            boolean hasNext = false;

            int indexToRead = (lastIndex + 1) % ringArray.length;

            if (ringArray[indexToRead] != null){
                hasNext = true;
            }

            return hasNext;
        }

        @Override
        public E next() throws NoSuchElementException {
            int nextIndex = (getTailPos() - moveAmount);

            if (nextIndex <= 0){
                nextIndex = nextIndex + 10;
            }

            if (ringArray[nextIndex] == null){
                throw  new NoSuchElementException();
            }

            return ringArray[nextIndex];
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    };
}




