import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node{
        private Item item;
        private Node pre;
        private Node next;
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException("should add something not null");
        }
        Node node = new Node();
        node.item = item;
        if(size == 0){
            first = node;
            last = node;
        }else{
            node.next = first;
            first.pre = node;
            first = first.pre;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException("should add something not null");
        }
        Node node = new Node();
        node.item = item;
        if(size == 0){
            first = node;
            last = node;
        }else{
            last.next = node;
            node.pre = last;
            last = last.next;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size == 0){
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        size--;
        if(size == 0){
            first = null;
            last = null;
        }else{
            Node delete = first;
            first = first.next;
            delete.next = null;
            first.pre = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size == 0){
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        size--;
        if(size == 0){
            first = null;
            last = null;
        }else{
            Node delete = last;
            last = last.pre;
            delete.pre = null;
            last.next = null;
        }
        return item;
    }

    private class DequeIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(current == null){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }


    // unit testing (required)
    public static void main(String[] args){

        // Deque<Integer> deque = new Deque<>();
        // System.out.println(deque.isEmpty() == true);
        // deque.addFirst(1);
        // System.out.println(deque.isEmpty() == false);
        // deque.addLast(2);
        // deque.addLast(3);
        // System.out.println(deque.size() == 3);
        // System.out.println(deque.removeFirst() == 1);
        // System.out.println(deque.removeLast() == 3);
        // System.out.println(deque.size() == 1);
        // Deque<String> deq = new Deque<>();
        // deq.addFirst("two");
        // deq.addFirst("one");
        // deq.addLast("three");
        // deq.addLast("four");
        // Iterator<String> it = deq.iterator();
        // System.out.println(it.hasNext() == true);
        // System.out.println(it.next() == "one");
        // System.out.println(it.next() == "two");
        // System.out.println(it.next() == "three");
        // System.out.println(it.next() == "four");
        // System.out.println(it.hasNext() == false);
        // System.out.println(it.next().equals(new NoSuchElementException()));
        // System.out.println(it.next().equals(new UnsupportedOperationException()));
    }

}
