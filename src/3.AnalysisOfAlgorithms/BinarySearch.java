// To compile: javac src/3.AnalysisOfAlgorithms/BinarySearch.java
// To run: java src/3.AnalysisOfAlgorithms/BinarySearchs
public class BinarySearch {

    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int key = 5;
        int index = binarySearch(a, key);
        if (index >= 0)
            System.out.println("Element found at index " + index);
        else
            System.out.println("Element not found in the array");
    }
}
