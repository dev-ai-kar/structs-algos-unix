// Space complexity: O(n)
// Time complexity: O(n) for delMax, O(1) for insert

// here unordered means that the items are not in any particular order
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // pq[i] = ith priority queue item
    private int N; // number of elements on priority queue

    // no generic array creation in Java, so we create an array of Object and cast
    // it to Key[]
    // This is only possible because Key extends Comparable
    // This is a cheat way because we are asking the client to provide a capacity
    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity]; // no generic array creation in Java
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (less(max, i))
                max = i;
        exch(max, N - 1);
        // null out the last item to avoid loitering
        return pq[--N];
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0; // This is only possible because Key extends Comparable
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<String> pq = new UnorderedMaxPQ<>(10);
        pq.insert("A");
        pq.insert("B");
        pq.insert("C");
        pq.insert("D");
        System.out.println(pq.delMax()); // should print D
        System.out.println(pq.delMax()); // should print C
        System.out.println(pq.delMax()); // should print B
        System.out.println(pq.delMax()); // should print A
    }
}