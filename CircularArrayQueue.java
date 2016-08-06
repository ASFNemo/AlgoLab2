import java.util.Arrays;
import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue{
    Integer queue[];
    int frontP = 0; // points to next empty space
    int endP = 0; // points to last item (next one to be taken off the queue)
    int count = 0; //number of items in array

    public CircularArrayQueue(int capacity){
        queue = new Integer[capacity];
        Arrays.fill(queue, null);
    }
    public CircularArrayQueue(){
        queue = new Integer[10];
        Arrays.fill(queue, null);
    }
    public void enqueue(int n){
        if (queue.length - count == 0){
            queue = doubleArray();
        }
        else{
            if(frontP >= queue.length){ //reached end of array
                frontP = 0;
            }
        }
        System.out.print("E" + n);
        queue[frontP] = n;

        frontP ++;
        if(frontP >= queue.length){ //reached end of array
            frontP = 0;
        }

        count++;
    }

    public int dequeue() throws NoSuchElementException{

        if(queue[endP] != null && count > 0){ //AF "&& queue[endP] != null"
            int itemInQ = queue[endP];
            queue[endP] = null;
            endP++;

            if (endP >= queue.length){
                //endP = 0;
                endP = endP%queue.length; //AF
                //System.out.println("hey ya'll, i am here"); //AF
            }
            count = count - 1;
            System.out.println("d" + itemInQ);
            return itemInQ;
        }
        else{
            NoSuchElementException e = new NoSuchElementException();
            throw e;
        }
    }

    public boolean isEmpty(){
        if(noItems() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    public int noItems(){
        int items = 0;
        for (int i = 0;i < queue.length ; i++){
            if(queue[i] != null){
                items++;
            }
        }
        return items;
    }
    public int getCapacityLeft(){
        return queue.length - noItems();
    }
    public Integer[] doubleArray(){
        Integer newQ[] = new Integer[queue.length*2];
        int nextItem;
        for (int i=0; i < queue.length; i++){
            if(endP + i < queue.length){
                nextItem = endP + i;
            }
            else{
                nextItem = endP + i - queue.length;
            }
            newQ[i] = queue[nextItem];
        }
        frontP = noItems();
        endP = 0;
        Arrays.fill(queue, null);
        return newQ;
    }
}