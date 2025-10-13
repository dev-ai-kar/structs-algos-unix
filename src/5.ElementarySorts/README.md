# Callbacks in Sorting Algorithms 🎯

## Problem: Sorting Different Data Types 🤔
- **Challenge**: How can a `sort()` function compare data of various types (e.g., `Double`, `String`, `java.io.File`) without prior knowledge of their keys?
- **Example**: 
  - Comparing two `Double` values numerically.
  - Comparing two `String` values lexicographically.
  - Comparing two `File` objects by pathname.

## Solution: Callbacks 📞
- **Callback**: A reference to executable code passed to the `sort()` function.
- **How it works**:
  1. **Client**: Passes an array of objects to `sort()`.
  2. **Sort Function**: Calls the object’s comparison method (e.g., `compareTo()`) **as needed**.
  3. **Comparison Logic**: Defined by the object itself, ensuring type-specific sorting.

---

## Implementing Callbacks Across Languages 🌐

### Java ☕
- **Mechanism**: **Interfaces** (e.g., `Comparable<T>`).
- **Example**:
  ```java
  public class Student implements Comparable<Student> {
      public int compareTo(Student other) { /* ... */ }
  }
  ```
- `sort()` calls `a.compareTo(b)` to compare objects.

### C 🖥️
- **Mechanism**: **Function pointers**.
- **Example**:
  ```c
  void qsort(void *base, size_t nmemb, size_t size,
             int (*compar)(const void *, const void *));
  ```
- Pass a function like `int compare(const void *a, const void *b)`.

### C++ ➕
- **Mechanism**: **Functors** (class-type objects overriding `operator()`).
- **Example**:
  ```cpp
  struct Compare {
      bool operator()(const File& a, const File& b) { /* ... */ }
  };
  std::sort(files.begin(), files.end(), Compare());
  ```

### C# 🔷
- **Mechanism**: **Delegates** (type-safe function pointers).
- **Example**:
  ```csharp
  Array.Sort(array, (a, b) => a.CompareTo(b));
  ```

### Python 🐍 / JavaScript 🟨
- **Mechanism**: **First-class functions**.
- **Python Example**:
  ```python
  sorted(files, key=lambda x: x.path)
  ```
- **JavaScript Example**:
  ```javascript
  array.sort((a, b) => a.localeCompare(b));
  ```

---

## Key Takeaway 🔑
- **Flexibility**: Callbacks let the `sort()` function stay generic while delegating type-specific logic to the objects.
- **Language Variations**: Each language uses its own paradigm (interfaces, pointers, functors, etc.), but the core idea remains the same: **decoupling sorting logic from comparison logic**.