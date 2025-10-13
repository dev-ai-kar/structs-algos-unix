// Compilation: javac -cp ".;lib/algs4.jar" src/2.Percolation/PercolationStats.java src/2.Percolation/Percolation.java -d build
// Execution: java -cp ".;build;lib/algs4.jar" PercolationStats 200 100

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;
    private final int trials;
    private int openedCount;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }
        this.trials = trials;
        thresholds = new double[trials];

        for (int t = 0; t < trials; t++) {
            Percolation percolation = new Percolation(n);
            int[] sites = new int[n * n];
            for (int i = 0; i < sites.length; i++) {
                sites[i] = i;
            }
            StdRandom.shuffle(sites);

            openedCount = 0;
            for (int i = 0; i < sites.length; i++) {
                int site = sites[i];
                int row = site / n + 1;
                int col = site % n + 1;
                percolation.open(row, col);
                if (percolation.percolates()) {
                    openedCount = i + 1;
                    break;
                }
            }
            thresholds[t] = (double) openedCount / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PercolationStats <n> <trials>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
