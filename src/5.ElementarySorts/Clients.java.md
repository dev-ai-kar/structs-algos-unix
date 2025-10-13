```java
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Sample client 1
// Usage: java Experiment 10
// Sorts 10 random numbers in ascending order
public class Experiment
{
   public static void main(String[] args)
   {
      int N = Integer.parseInt(args[0]);
      Double[] a = new Double[N];
      for (int i = 0; i < N; i++)
         a[i] = StdRandom.uniform();
      Insertion.sort(a); // a is a reference to the array of doubles
      for (int i = 0; i < N; i++)
         StdOut.println(a[i]);
   }
}

// Sample client 2
// Usage: java StringSorter < words3.txt
public class StringSorter
{
   public static void main(String[] args)
   {
      String[] a = In.readStrings(args[0]);
      // String[] a = In.readAllStrings(args[0]);
      Insertion.sort(a);// here a is a reference to the array of strings
      for (int i = 0; i < a.length; i++)
         StdOut.println(a[i]);
   }
}

// Sample client 3
// import java.io.File;
public class FileSorter
{
   public static void main(String[] args)
   {
      File directory = new File(args[0]);
      File[] files = directory.listFiles();
      Insertion.sort(files);// here files is a reference to the array of files
      for (int i = 0; i < files.length; i++)
         StdOut.println(files[i].getName());
   }

   
}
```
# Useful sorting abstactions
## Helper Fuctions
1. Less
```java
// Helper function
private static boolean less(Comparable v, Comparable w){return v.compareTo(w) <0;}
```

2.Exchange
```java
private static void exch(Comparable[]a, int i, int j)
{
   Comparable swap = a[i];
   a[i] = a[j];
   a[j] = swap;
}
```