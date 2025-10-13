```java
// Challenge: Find the largest M items in a stream of N items
// Solution: Use a min-oriented priority queue to find the M largest items


// use a min-oriented priority queue to find the M largest transactions
// Transaction data type is Comparable (ordered by $$)
MinPQ<Transaction> pq = new MinPQ<Transaction>();


while (StdIn.hasNextLine())
{
    String line = StdIn.readLine();
    Transaction item = new Transaction(line);
    pq.insert(item);
    // pq contains largest M items seen so far
    if (pq.size() > M)
        pq.delMin();
}
```

```java
private void swin(int k)
{
    while (k > 1 && less(k/2, k))
    {
        exch(k, k/2);
        k = k/2;
    }
}

public void insert(Key x)
{
    pq[++N] = x;
    swim(N);
}
```