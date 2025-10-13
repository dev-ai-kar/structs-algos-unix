import java.util.Objects;

/**
 * Selection sort implementation that sorts Comparable objects in ascending order.
 * Time Complexity: O(n²) for all cases
 * Space Complexity: O(1)
 */
@SuppressWarnings("rawtypes")
public class Selection {
    /**
     * Sorts the array in ascending order using Selection sort algorithm.
     * @param a the array to be sorted
     * @throws IllegalArgumentException if the input array is null
     */
    public static void sort(Comparable[] a) {
        Objects.requireNonNull(a, "Input array cannot be null");
        
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Prints the array elements to standard output.
     * @param a the array to be printed
     * @throws IllegalArgumentException if the input array is null
     */
    public static void show(Comparable[] a) {
        Objects.requireNonNull(a, "Input array cannot be null");
        
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}