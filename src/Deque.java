import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private final Node sentinel;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;

        private Node(Item item) {
            this.item = item;
            prev = null;
            next = null;
        }
    }

    // construct an empty deque
    public Deque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null");

        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;

        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null");

        Node newNode = new Node(item);

        newNode.next = sentinel;
        newNode.prev = sentinel.prev;

        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("Can't remove from empty list");

        Node temp = sentinel.next;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size -= 1;

        return temp.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("Can't remove from empty list");

        Node temp = sentinel.prev;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size -= 1;

        return temp.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new dListIterator();
    }

    private class dListIterator implements Iterator<Item> {
        Node curr = sentinel.next;

        @Override
        public boolean hasNext() {
            return curr != sentinel;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Node temp = curr;
            curr = curr.next;

            return temp.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;

        Deque<Integer> dList = new Deque<>();

        System.out.println("List is empty: " + dList.isEmpty());

        for (int i = 0; i < n; i++) {
            dList.addLast(i);
        }

        System.out.println("Size of dList is " + dList.size());

        for (int a : dList) {
            for (int b : dList)
                System.out.print(a + "-" + b + " ");
            System.out.println();
        }

        System.out.println("List is empty: " + dList.isEmpty());

        for (int i = 0; i < n; i++) {
            System.out.print(dList.removeLast() + "- ");
            System.out.println();
        }

        System.out.println("List is empty: " + dList.isEmpty());
    }

}
