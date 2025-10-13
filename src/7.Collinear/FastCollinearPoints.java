/*
 * Title: FastCollinearPoints.java
 * 
 * Compilation: 
 javac -cp ".;lib/algs4.jar" src/7.Collinear/Point.java src/7.Collinear/LineSegment.java src/7.Collinear/FastCollinearPoints.java -d build
 * 
 * Execution cmd:  
 java -cp ".;build;lib/algs4.jar" FastCollinearPoints data/input8.txt
 *
 * Execution pwsh: 
 java -cp ".;build;lib\algs4.jar" FastCollinearPoints .\data\input8.txt 
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Finds all maximal line segments containing at least four points from a given
 * set of points.
 * This implementation uses a sorting-based approach with a time complexity of
 * O(n² log n).
 * <p>
 * For each point, the algorithm sorts the other points by the slope they make
 * with the current point.
 * Collinear points are identified as groups of adjacent points in the sorted
 * array with equal slopes.
 * To ensure maximal segments and avoid duplicates, a line segment is added only
 * if the current point
 * is the smallest in the naturally ordered group of collinear points.
 */
public class FastCollinearPoints {
    private final LineSegment[] segments;

    /**
     * Constructs a FastCollinearPoints object to find all line segments with 4 or
     * more collinear points.
     *
     * @param points the array of points to analyze; must not be null or contain
     *               null points
     * @throws IllegalArgumentException if the input array is null, any point is
     *                                  null, or there are duplicate points
     */
    public FastCollinearPoints(Point[] points) {
        validate(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        checkDuplicates(pointsCopy);

        List<LineSegment> segmentsList = new ArrayList<>();

        for (int i = 0; i < pointsCopy.length; i++) {
            Point p = pointsCopy[i];
            Point[] others = new Point[pointsCopy.length - 1];
            System.arraycopy(pointsCopy, 0, others, 0, i);
            System.arraycopy(pointsCopy, i + 1, others, i, pointsCopy.length - i - 1);

            // Sort by slope with respect to point p
            Arrays.sort(others, p.slopeOrder());

            int current = 0;
            while (current < others.length) {
                int next = current + 1;
                // Find the end of the current slope group
                while (next < others.length && p.slopeTo(others[current]) == p.slopeTo(others[next])) {
                    next++;
                }
                // Check if the group has at least 3 points (4 including p)
                if (next - current >= 3) {
                    List<Point> group = new ArrayList<>();
                    group.add(p);
                    for (int k = current; k < next; k++) {
                        group.add(others[k]);
                    }
                    // Sort the group to determine natural order
                    Collections.sort(group);
                    // Add the segment only if p is the smallest point (to avoid duplicates)
                    if (group.get(0) == p) {
                        segmentsList.add(new LineSegment(group.get(0), group.get(group.size() - 1)));
                    }
                }
                current = next;
            }
        }

        segments = segmentsList.toArray(new LineSegment[0]);
    }

    /**
     * Validates the input array of points.
     *
     * @param points the array to validate
     * @throws IllegalArgumentException if the array is null or contains null points
     */
    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Array contains null points");
            }
        }
    }

    /**
     * Checks for duplicate points in the sorted array.
     *
     * @param points the sorted array of points
     * @throws IllegalArgumentException if duplicate points are found
     */
    private void checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points");
            }
        }
    }

    /**
     * Returns the number of maximal line segments found.
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * Returns an array of all maximal line segments found.
     * The returned array is a defensive copy to ensure immutability.
     *
     * @return an array of LineSegment objects
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    /**
     * Unit tests the FastCollinearPoints data type.
     *
     * @param args the command-line arguments
     */
    // Test client (example usage)
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}