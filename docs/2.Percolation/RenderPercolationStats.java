/*
javac -cp ".;lib/algs4.jar" src/2.Percolation/RenderPercolationStats.java src/2.Percolation/PercolationStats.java src/2.Percolation/Percolation.java -d build
java -cp ".;build;lib/algs4.jar" RenderPercolationStats 20 5
 */

 import edu.princeton.cs.algs4.StdDraw;
 import edu.princeton.cs.algs4.StdRandom;
 
 public class RenderPercolationStats {
     private final int n;
     private final double cellSize;
     private static final int DELAY = 200; // Animation speed (ms)
 
     public RenderPercolationStats(int n) {
         this.n = n;
         this.cellSize = 1.0 / n;
         initializeCanvas();
     }
 
     private void initializeCanvas() {
         StdDraw.enableDoubleBuffering();
         StdDraw.setCanvasSize(1000, 1000);
         StdDraw.setScale(-0.2, 1.2);
     }
 
     public void visualizePercolation(int n, int trials) {
         for (int t = 0; t < trials; t++) {
             Percolation percolation = new Percolation(n);
             visualizeTrial(percolation, t + 1, trials);
         }
     }
 
     private void visualizeTrial(Percolation percolation, int currentTrial, int totalTrials) {
         int[] sites = new int[n * n];
         for (int i = 0; i < sites.length; i++) sites[i] = i;
         StdRandom.shuffle(sites);
 
         for (int i = 0; i < sites.length; i++) {
             int row = sites[i] / n + 1;
             int col = sites[i] % n + 1;
             percolation.open(row, col);
             
             drawGrid(percolation, i + 1, currentTrial, totalTrials);
             
             if (percolation.percolates()) {
                 StdDraw.pause(1000);
                 break;
             }
         }
     }
 
     private void drawExplanation(Percolation percolation, int openSites, int trial, int totalTrials) {
         // Title and trial information
         StdDraw.setPenColor(StdDraw.BLACK);
         StdDraw.text(0.5, 1.15, "Percolation Simulation - Trial " + trial + "/" + totalTrials);
         
         // Legend
         drawLegendItem(0.1, 1.1, StdDraw.BLACK, "Blocked Site");
         drawLegendItem(0.1, 1.05, StdDraw.WHITE, "Open Site");
         drawLegendItem(0.1, 1.0, StdDraw.BLUE, "Full Site (Connected to Top)");
 
         // Statistics
         String stats = String.format("Open Sites: %d/%d (%.1f%%)", 
             openSites, n * n, (100.0 * openSites) / (n * n));
         StdDraw.text(0.5, -0.05, stats);
 
         // System state
         String state = percolation.percolates() ? 
             "System PERCOLATES! (Path exists from top to bottom)" :
             "System does NOT percolate yet";
         StdDraw.setPenColor(percolation.percolates() ? StdDraw.BLUE : StdDraw.RED);
         StdDraw.text(0.5, -0.1, state);
     }
 
     private void drawLegendItem(double x, double y, java.awt.Color color, String text) {
         StdDraw.setPenColor(color);
         StdDraw.filledSquare(x, y, 0.02);
         StdDraw.setPenColor(StdDraw.BLACK);
         StdDraw.text(x + 0.2, y, text);
     }
 
     private void drawGrid(Percolation percolation, int openSites, int trial, int totalTrials) {
         StdDraw.clear();
         drawExplanation(percolation, openSites, trial, totalTrials);
 
         for (int row = 1; row <= n; row++) {
             for (int col = 1; col <= n; col++) {
                 double x = (col - 0.5) * cellSize;
                 double y = 1 - (row - 0.5) * cellSize;
 
                 java.awt.Color color = StdDraw.BLACK;
                 if (percolation.isOpen(row, col)) {
                     color = percolation.isFull(row, col) ? StdDraw.BLUE : StdDraw.WHITE;
                 }
 
                 StdDraw.setPenColor(color);
                 StdDraw.filledSquare(x, y, cellSize/2);
                 StdDraw.setPenColor(StdDraw.GRAY);
                 StdDraw.square(x, y, cellSize/2);
             }
         }
         StdDraw.show();
         StdDraw.pause(DELAY);
     }
 
     public static void main(String[] args) {
         if (args.length != 2) {
             System.err.println("Usage: java RenderPercolationStats <grid size> <trials>");
             System.exit(1);
         }
 
         try {
             int n = Integer.parseInt(args[0]);
             int trials = Integer.parseInt(args[1]);
             
             if (n <= 0 || trials <= 0) {
                 throw new IllegalArgumentException();
             }
 
             RenderPercolationStats renderer = new RenderPercolationStats(n);
             renderer.visualizePercolation(n, trials);
             
         } catch (NumberFormatException e) {
             System.err.println("Error: Both arguments must be integers.");
             System.exit(1);
         } catch (IllegalArgumentException e) {
             System.err.println("Error: Grid size and trials must be positive integers.");
             System.exit(1);
         }
     }
 }