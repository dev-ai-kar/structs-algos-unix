# Stacks and Queues: Implementation Considerations

## Stack Implementations

### Array-Based Stack
- **Advantages**:
  - ⚡ **Faster operations** due to cache locality (contiguous memory blocks).
  - 💾 **Lower memory overhead** (no storage for node pointers).
  - 🔄 **Amortized O(1)** performance for push/pop (with dynamic resizing).
- **Disadvantages**:
  - 📏 **Fixed capacity** (requires costly resizing for growth).
  - ⏳ **Latency spikes** during array resizing.

### Linked List-Based Stack
- **Advantages**:
  - ∞ **Dynamic sizing** (no resizing logic needed).
  - ⏱️ **Predictable O(1)** per-operation performance.
  - 🛡️ **No resizing delays** (critical for real-time systems).
- **Disadvantages**:
  - 🧩 **Poor cache locality** (nodes scattered in memory).
  - 📦 **Higher memory usage** (extra storage for node pointers).

---

## Queue Implementations

### Array-Based Queue (Circular Buffer)
- **Advantages**:
  - 🚀 **Cache-efficient** (contiguous memory access).
  - 📉 **Compact storage** (no pointer overhead).
  - 💨 **Fast enqueue/dequeue** when within capacity.
- **Disadvantages**:
  - 🔒 **Fixed size** (complex wraparound logic for overflow).
  - 🚧 **Costly resizing** requires full array copy.

### Linked List-Based Queue
- **Advantages**:
  - 🔄 **Unbounded capacity** (grows seamlessly).
  - 🎯 **Consistent O(1)** per-operation time.
  - 🧠 **Simple growth logic** (no resizing needed).
- **Disadvantages**:
  - 🐌 **Slower per-operation** (memory allocation + pointer chasing).
  - 🧠 **Memory fragmentation** from scattered nodes.

---

## When to Use Each Implementation

| Scenario                        | Recommended Implementation     |
|---------------------------------|---------------------------------|
| Fixed/known maximum size        | **Array-based** (Stack/Queue)   |
| Memory-constrained environment | **Array-based** (Stack/Queue)   |
| Real-time systems               | **Linked List-based** (Stack/Queue) |
| Highly dynamic data sizes       | **Linked List-based** (Stack/Queue) |
| Throughput-oriented workloads   | **Array-based** (Stack/Queue)   |

---

## Key Considerations
1. **Latency vs Throughput**:
   - Arrays favor throughput (batch processing).
   - Linked lists favor predictable latency (real-time systems).

2. **Memory Tradeoffs**:
   - Arrays waste space if underfilled but are compact when full.
   - Linked lists use 40-100% extra memory for pointers (e.g., 16B overhead per node in Java).

3. **Resizing Strategies**:
   - Array resizing typically doubles capacity (amortized O(1) cost).
   - Linked lists never require resizing but constantly allocate memory.

---

## Iterator Support
Both implementations can support iteration. Linked lists allow trivial forward iteration, while array-based structures require tracking indices. For implementation details, see [Iterable.md](Iterable.md).

### Example: Stack Iterator (Array vs Linked List)
```java
// Array-based stack iterator
public class ArrayStackIterator implements Iterator<Item> {
    private int current = topIndex;
    
    public boolean hasNext() { return current >= 0; }
    public Item next() { return array[current--]; }
}

// Linked list-based stack iterator
public class LinkedListStackIterator implements Iterator<Item> {
    private Node current = top;
    
    public boolean hasNext() { return current != null; }
    public Item next() { 
        Item item = current.data;
        current = current.next;
        return item;
    }
}
```
# Information on Java Generics
Java prohibits generic array creation due to the interplay between generics (using type erasure) and arrays (being reified), which can lead to type safety violations. Here's the breakdown:

1. **Type Erasure vs. Reified Arrays**:
   - **Generics**: Use type erasure, meaning generic type information is removed at runtime. For example, `List<String>` becomes `List` at runtime.
   - **Arrays**: Know their component type at runtime (reified). An array enforces type checks during element insertion (e.g., `String[]` rejects an `Integer`).

2. **Conflict and Risks**:
   - If generic arrays were allowed, the runtime component type would be the raw type (e.g., `List[]` instead of `List<String>[]`). This mismatch could lead to:
     - **Heap Pollution**: Inserting incompatible types (e.g., a `List<Integer>` into a `List<String>[]`), causing unexpected `ClassCastException`s later.
     - **Bypassing Compile-Time Checks**: Code using raw types or unchecked casts could corrupt the array, violating type safety guarantees.

3. **Example Scenario**:
   ```java
   // Hypothetically allowed (but actually illegal):
   List<String>[] stringLists = new List<String>[1];
   Object[] objects = stringLists;
   objects[0] = new ArrayList<Integer>(); // No runtime error (ArrayStoreException expected but not thrown)
   String s = stringLists[0].get(0); // ClassCastException at runtime!
   ```
   The array cannot enforce `List<String>` at runtime, leading to undetected type mismatches.

4. **Java’s Design Choice**:
   - Prohibiting generic arrays prevents these risks, ensuring type safety. Developers use `List` (e.g., `ArrayList`) instead of arrays when generics are needed, avoiding these issues.

**Conclusion**: Java disallows generic arrays to maintain type safety, as runtime checks for generics are erased, conflicting with arrays' reified types. This avoids heap pollution and ensures compile-time type checks remain reliable.