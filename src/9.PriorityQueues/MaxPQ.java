/*************************
 * 
 * MaxPQ.java
 * Implementation of a priority queue using a binary heap.
 * 
 ************************/
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // pq[i] = ith priority queue item
    private int N; // number of elements on priority queue

    // no generic array creation in Java, so we create an array of Object and cast
    // it to Key[]
    // This is only possible because Key extends Comparable
    // This is a cheat way because we are asking the client to provide a capacity
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; // no generic array creation in Java
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[++N] = x; // increment N and assign x to pq[N]
        swim(N); // restore the heap property by swimming the new item up
    }

    public Key delMax() {
        Key max = pq[1]; // the maximum item is at the root
        exch(1, N--); // exchange it with the last item and decrement N
        sink(1); // restore the heap property by sinking the root down
        pq[N + 1] = null; // avoid loitering
        return max; // return the maximum item
    }

    private void swim(int k) { // here k is the index of the item to be moved up
        while (k > 1 && less(k / 2, k)) { // while the item is not at the root and is greater than its parent
            exch(k, k / 2); // exchange the item with its parent
            k = k / 2; // move up to the parent's index
        }
    }

    private void sink(int k) { // here k is the index of the item to be moved down
        while (2 * k <= N) { // while the item has at least one child
            int j = 2 * k; // left child index
            if (j < N && less(j, j + 1))
                j++; // if right child exists and is greater, move to right child
            if (!less(k, j))
                break; // if the item is less than or equal to the largest child, stop
            exch(k, j); // exchange the item with the largest child
            k = j; // move down to the child's index
        }
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
}
