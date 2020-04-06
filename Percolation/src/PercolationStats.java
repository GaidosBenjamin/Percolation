import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    private final int [] trial;
    private final static double CONST_96 = 1.96;

    public PercolationStats(int n, int trials) {
        trial = new int[trials];
        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation p = new Percolation(n);
            while (true) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count++;
                }
                if (p.percolates())
                    break;
            }
            trial[i] = count;
        }

    }

    public double mean() {
        return StdStats.mean(trial);
    }

    public double stddev() {
        return StdStats.stddev(trial);
    }

    public double confidenceLo() {
        return mean() - ((CONST_96 * stddev()) / Math.sqrt(trial.length));
    }

    public double confidenceHi() {
        return mean() + ((CONST_96 * stddev()) / Math.sqrt(trial.length));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
