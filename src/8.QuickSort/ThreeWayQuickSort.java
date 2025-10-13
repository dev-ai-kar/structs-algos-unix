
@SuppressWarnings("rawtypes")
public class ThreeWayQuickSort {
    private static void sort( Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo + 1;
        
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++); // a[i] < v
            else if (cmp > 0) exch(a, i, gt--); // a[i] > v
            else i++; // a[i] == v
        }
        sort(a, lo, lt - 1); // Sort the left part
        sort(a, gt + 1, hi); // Sort the right part
    }

    private static void exch( Comparable[] a, int i, int j) {
        
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Comparable[] a) {
        // Shuffle the array to guarantee performance
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        // String[] a = {"E", "D", "C", "B", "A"};
        // Test with Duplicate keys with 15 elements
        String[] a = {"E", "D", "C", "B", "A", "A", "B", "C", "D", "E", "E", "D", "C", "B", "A"};
        ThreeWayQuickSort.sort(a);
        for (String s : a) {
            System.out.print(s + " ");
        }
    }
    // Output: A B C D E
    // Output: A A B B C C D D E E
    // Output: A A A B B B C C C D D D E E E
}
