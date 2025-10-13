/*
Merging:  Java implementation
       lo    i mid   j   hi
aux[]  A G L O R H I M S T
a[]    A G H I L M

 */
@SuppressWarnings("rawtypes")
public class MergeBU {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
        assert isSorted(a, mid + 1, hi); // precondition: a[mid+1..hi] sorted

        // copy
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;

        // merge.

        for (int k = lo; k <= hi; k++) {// Switch roles of a and aux
            if (i > mid)
                aux[k] = a[j++];
            else if (j > hi)
                aux[k] = a[i++];
            else if (less(a[j], a[i]))
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
        }
        assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
    }

    // private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    // {
    // // Improvement 1: Use insertion sort for small subarrays
    // final int CUTOFF = 4;
    // if (hi <= lo + CUTOFF - 1)
    // {
    // Insertion.sort(a, lo, hi);
    // return;
    // }
    // int mid = lo + (hi - lo) / 2;
    // sort(aux, a, lo, mid);
    // sort(aux, a, mid+1, hi);
    // // Improvement 2: Stop if already sorted
    // if (!less(a[mid+1], a[mid])) return;
    // merge(a, aux, lo, mid, hi);

    // }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        String[] a = { "A", "G", "L", "O", "R", "H", "I", "M", "S", "T" };
        sort(a);
        show(a);
    }
}