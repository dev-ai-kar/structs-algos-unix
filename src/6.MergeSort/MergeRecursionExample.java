@SuppressWarnings("rawtypes") 
public class MergeRecursionExample {
    public static void main(String[] args) {
        String[] a = { "A", "G", "L", "O", "R", "H", "I", "M", "S", "T" };
        System.out.println("Initial array: " + String.join(" ", a));
        sort(a, 0, a.length - 1);
        System.out.println("Final array:   " + String.join(" ", a));
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            System.out.println("Base case: lo = " + lo + ", hi = " + hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;

        System.out.println("Split left:  [" + lo + "..." + mid + "]");
        sort(a, lo, mid);

        System.out.println("Split right: [" + (mid + 1) + "..." + hi + "]");
        sort(a, mid + 1, hi);

        System.out.println("Merge: [" + lo + "..." + mid + "] and [" + (mid + 1) + "..." + hi + "]");
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}