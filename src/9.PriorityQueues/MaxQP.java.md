```java
import edu.princeton.cs.algs4.MaxPQ;

public class MaxQP<Key extends Comparable<Key>> {
   // create an empty max priority queue
   MaxPQ()
   // create a priority queue with given keys
   MaxPQ(Key[] a)
   // insert a key into the priority queue
   void insert(Key v)
   // remove and return the largest key from the priority queue
   Key delMax()
   // is the priority queue empty?
   boolean isEmpty()
   // return the largest key in the priority queue without removing it
   Key max()
   // return the number of keys in the priority queue
   int size()
}
```