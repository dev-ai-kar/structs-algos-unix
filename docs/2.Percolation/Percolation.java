// Compilation: javac -cp ".;lib/algs4.jar" src/2.Percolation/Percolation.java -d build
// Execution: java -cp ".;build;lib/algs4.jar" Percolation
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] grid;
    public WeightedQuickUnionUF uf1; // Includes virtual top and bottom
    public WeightedQuickUnionUF uf2; // Includes virtual top only
    private int virtualTop1;

    private int virtualBottom1;
    private int virtualTop2;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        grid = new boolean[n][n];
        openSites = 0; // Initially all sites are blocked

        // virtual top and bottom for percolation test
        uf1 = new WeightedQuickUnionUF(n * n + 2);
        // only virtual top for fill test
        uf2 = new WeightedQuickUnionUF(n * n + 1);

        virtualTop1 = n*n;
        virtualBottom1 = n*n+1;
        virtualTop2 = n*n;

    }

    // Assumption is the that row and col start from 1
    private int getIndex(int row, int col)
    {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Row or column out of bounds");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        validate(row, col);
        if (isOpen(row, col)) return;
        grid[row-1][col-1] = true;
        openSites ++;

        int currentIndex = getIndex(row, col);
        // Check up, down , left, right
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] dir:directions) {
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];
            if (adjRow >= 1 && adjRow <= n && adjCol >=1 && adjCol <=n && isOpen(adjRow,adjCol))
            {
                int adjIndex = getIndex(adjRow,adjCol);
                uf1.union(currentIndex, adjIndex);
                uf2.union(currentIndex, adjIndex);
            }
        }

        if (row == 1) {
            uf1.union(currentIndex, virtualTop1);
            uf2.union(currentIndex, virtualTop2);
        }
        if (row == n) {
            uf1.union(currentIndex, virtualBottom1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        validate(row, col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        validate(row, col);
        return isOpen(row, col) && uf2.find(getIndex(row, col)) == uf2.find(virtualTop2);
    }

    // returns the number of open sites
    public int numberOfOpenSites() { return openSites;}

    // does the system percolate?
    public boolean percolates()
    {
        return uf1.find(virtualTop1) == uf1.find(virtualBottom1);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Test 1: Open first cell and check basic properties
        Percolation p1 = new Percolation(3);
        p1.open(1, 1);
        System.out.println("Test 1 - Should be open: " + p1.isOpen(1, 1));       // true
        System.out.println("Test 1 - Should be full: " + p1.isFull(1, 1));       // true
        System.out.println("Test 1 - Percolates? " + p1.percolates() + "\n");    // false

        // Test 2: Test 1x1 grid
        Percolation p2 = new Percolation(1);
        System.out.println("Test 2 - Initial percolates? " + p2.percolates());   // false
        p2.open(1, 1);
        System.out.println("Test 2 - Should percolate: " + p2.percolates() + "\n"); // true

        // Test 3: Simple vertical percolation
        Percolation p3 = new Percolation(3);
        p3.open(1, 1);
        p3.open(2, 1);
        p3.open(3, 1);
        System.out.println("Test 3 - Should percolate: " + p3.percolates());     // true
        System.out.println("Test 3 - (3,1) should be full: " + p3.isFull(3, 1) + "\n"); // true

        // Test 4: Horizontal connection (should not percolate)
        Percolation p4 = new Percolation(3);
        p4.open(1, 1);
        p4.open(1, 2);
        p4.open(1, 3);
        System.out.println("Test 4 - Should not percolate: " + p4.percolates() + "\n"); // false

        // Test 5: Check backwash prevention
        Percolation p5 = new Percolation(3);
        p5.open(1, 1);
        p5.open(2, 1);
        p5.open(3, 1);  // This connects to bottom
        p5.open(3, 3);  // This should not be full
        System.out.println("Test 5 - (3,3) should not be full: " + p5.isFull(3, 3) + "\n"); // false

        // Test 6: Invalid constructor
        try {
            @SuppressWarnings("unused")
            Percolation p6 = new Percolation(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 6 - Caught invalid constructor: " + e.getMessage() + "\n");
        }

        // Test 7: Boundary checks
        try {
            Percolation p7 = new Percolation(2);
            p7.open(0, 1);  // Should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 - Caught invalid open: " + e.getMessage() + "\n");
        }
    }
}
