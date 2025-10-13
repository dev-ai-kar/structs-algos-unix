// Improved space complexity of merge sort by using an auxiliary array of size n instead of 2n

@SuppressWarnings("rawtypes")
public class MergeWithSmallerAuxiliary {
    @SuppressWarnings("unchecked")
    public static void merge(Comparable[] a, int n) {
        Comparable[] aux = new Comparable[n];
        // Copy the first half to the auxiliary array
        for (int i = 0; i < n; i++) {
            aux[i] = a[i];
        }

        int i = 0; // Pointer for aux
        int j = n; // Pointer for second half of a
        int k = 0; // Pointer for merged array

        // Merge aux and the second half back into a
        while (i < n && j < 2 * n) {
            // If aux[i] <= a[j], copy aux[i] to a[k] and increment i and k
            if (aux[i].compareTo(a[j]) <= 0) {
                a[k++] = aux[i++];
            } else {
                a[k++] = a[j++];
            }
        }

        // Copy remaining elements from aux if any
        while (i < n) {
            a[k++] = aux[i++];
        }
        // No need to copy remaining elements from a[j..2n-1] as they are already in
        // place
        
    }
    
}