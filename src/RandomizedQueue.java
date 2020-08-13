import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    private static final int DEFAULT_SIZE = 20;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null");

        if (queue.length == size) resize(queue.length * 2);

        if (size == 0) {
            queue[size++] = item;
            return;
        }

        int index = StdRandom.uniform(size);

        Item temp = queue[index];
        queue[index] = item;
        queue[size++] = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("Can't remove from empty list");

        int index = StdRandom.uniform(size);

        Item temp = queue[index];
        queue[index] = queue[size -= 1];

        if (size == queue.length / 4) resize(queue.length / 2);

        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException("Empty list");

        return queue[StdRandom.uniform(size)];
    }

    private void resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        System.arraycopy(queue, 0, newQueue, 0, size);
        queue = newQueue;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new queueIterator();
    }

    private class queueIterator implements Iterator<Item> {
        int index;
        int[] randomIndex;

        private queueIterator() {
            randomIndex = new int[size];
            index = 0;

            for (int i = 0; i < size; i++) {
                randomIndex[i] = i;
            }

            StdRandom.shuffle(randomIndex);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return queue[randomIndex[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 30; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("sample:" + queue.sample());
        System.out.println("dequeue");
        while (!queue.isEmpty()) System.out.println(queue.dequeue());
        System.out.println("Size is " + queue.size());
    }

}
