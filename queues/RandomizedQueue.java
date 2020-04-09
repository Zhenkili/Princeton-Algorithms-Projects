import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] queue;


    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 0;
        queue = (Item[]) new Object[4];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        if(size == queue.length){
            resize();
        }
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        if(size <= queue.length / 4){
            shrink();
        }
        int index = StdRandom.uniform(size);
        Item remove_element = queue[index];
        queue[index] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        return remove_element;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        return queue[index];
    }

    public void resize(){
        int newlength = 2 * queue.length;
        Item[] newqueue = (Item[]) new Object[newlength];
        for(int i = 0; i < queue.length; i++){
            newqueue[i] = queue[i];
        }
        queue = newqueue;
    }

    public void shrink(){
        int newlength = queue.length / 2;
        Item[] newqueue = (Item[]) new Object[newlength];
        for(int i = 0; i < queue.length; i++){
            newqueue[i] = queue[i];
        }
        queue = newqueue;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{

        private int indexsize;
        private int[] indexes;

        public RandomIterator(){
            indexsize = size;
            indexes = new int[size];
            for(int i = 0; i < size; i++){
                indexes[i] = i;
            }
        }

        @Override
        public boolean hasNext() {
            return indexsize != 0;
        }

        @Override
        public Item next() {
            if(indexsize == 0){
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniform(indexsize);
            Item next_element = queue[indexes[index]];
            indexes[index] = indexes[indexsize-1];
            indexsize--;
            return next_element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        System.out.println(rq.isEmpty() == true);
        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        System.out.println(rq.isEmpty() == false);
        System.out.println(rq.size == 8);
        System.out.println("sample");
        System.out.println(rq.sample());
        System.out.println(rq.sample());
        System.out.println(rq.sample());
        System.out.println(rq.sample());
        System.out.println("deque");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println("iterator");
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        Iterator<String> it = rq.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
