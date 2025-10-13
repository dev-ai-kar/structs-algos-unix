import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to find all line segments containing 4 or more collinear points using a brute-force approach.
 * This implementation checks all combinations of 4 points and compares their slopes to determine collinearity.
 * The time complexity is O(n^4) in the worst case.
 */
public class BruteCollinearPoints {
    private final LineSegment[] segments;

    /**
     * Finds all line segments containing 4 collinear points.
     * 
     * @param points The array of points to analyze.
     * @throws IllegalArgumentException if the input array is null, contains null points, or has duplicate points.
     */
    public BruteCollinearPoints(Point[] points) {
        // Validate input
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }

        // Check for null points
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Point in array is null");
            }
        }

        // Sort to check for duplicates and organize points
        int n = points.length;
        // is this a deep copy?
        // the answer is no, it is a shallow copy
        Point[] sorted = points.clone();
        Arrays.sort(sorted);

        // Check for duplicate points
        for (int i = 0; i < n - 1; i++) {
            if (sorted[i].compareTo(sorted[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points found");
            }
        }

        List<LineSegment> segmentsList = new ArrayList<>();

        // Check all combinations of 4 points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = sorted[i];
                        Point q = sorted[j];
                        Point r = sorted[k];
                        Point s = sorted[l];

                        // Check if all 4 points are collinear
                        double slopeQ = p.slopeTo(q);
                        double slopeR = p.slopeTo(r);
                        double slopeS = p.slopeTo(s);

                        if (Double.compare(slopeQ, slopeR) == 0 && Double.compare(slopeR, slopeS) == 0) {
                            // Add the maximal segment (from first to last point)
                            segmentsList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

        // Store the results
        segments = segmentsList.toArray(new LineSegment[0]);
    }

    /**
     * Returns the number of line segments containing 4 collinear points.
     * 
     * @return The number of line segments.
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * Returns an array of all line segments containing 4 collinear points.
     * The array is a copy to ensure immutability.
     * 
     * @return The array of line segments.
     */
    public LineSegment[] segments() {
        return segments.clone();
    }

    // Test client (example usage)
    public static void main(String[] args) {
        // Create sample points
        Point[] points = {
            new Point(0, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(3, 3),  // Collinear with the first 3
            new Point(1, 2),
            new Point(2, 1)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        
        // Print the number of segments
        System.out.println("Number of segments: " + collinear.numberOfSegments());
        
        // Print each segment
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
        }
    }
}
