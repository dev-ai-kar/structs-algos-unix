
@SuppressWarnings("rawtypes")
public class QuickSort{

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch( Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // Find item on left(lo) to swap.
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
                
            }
            // Find item on right(hi) to swap.
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            // Check if pointers cross.
            if (i >= j) break;
            exch(a, i, j); // Swap the items at i and j
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        // Shuffle the array to guarantee performance
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition the array
        sort(a, lo, j - 1); // Sort the left half
        sort(a, j + 1, hi); // Sort the right half
    }

    public static void main(String[] args) {
        String[] a = {"E", "D", "C", "B", "A"};
        QuickSort.sort(a);
        for (String s : a) {
            System.out.print(s + " ");
        }
    }
    // Output: A B C D E
}
