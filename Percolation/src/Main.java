import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Main {

    public static void maine(String [] args) {
	// write your code here
        int n = 20;
        try {
            for (int i = 0; i < 60; i++) {
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
                System.out.println(count);
            }

        } catch(IllegalArgumentException ex) {
            System.out.println("Eroare!");
        }


    }
}
