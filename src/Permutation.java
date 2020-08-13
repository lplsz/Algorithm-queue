public class Permutation {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < number; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
