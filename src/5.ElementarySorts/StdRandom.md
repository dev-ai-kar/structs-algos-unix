```java
    // Knuth shuffle
// 60 between 0 and i

public class StdRandom
{
   ...
   public static void shuffle(Object[] a)
   {
      int N = a.length;
      for (int i = 0; i < N; i++)
      {
         int r = StdRandom.uniform(i + 1);
         exch(a, i, r);
      }
   }
}
// common bug: between 0 and N — 1
// correct variant: between i and N — 1
```
