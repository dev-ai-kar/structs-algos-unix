@SuppressWarnings("rawtypes")
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;
    }

    // Adding this signature to support sorting a subarray especially for MergeSort
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        /* as before */ 
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        /* as before */
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
