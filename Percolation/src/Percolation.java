import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private int count;
    private final int length;
    private final int n;

    // false - site blocked
    // true - site open
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.length = n * n + 2;
        this.n = n;
        uf = new WeightedQuickUnionUF(length);
        uf2 = new WeightedQuickUnionUF(length - 1);
        grid = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = false;
            }
        }
        count = 0;
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.find(0) == uf2.find(row * n + col - n);
    }

    private void validate(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1)
            throw new IllegalArgumentException();
    }

    private void south(int row, int col) {
        if (grid[row + 1][col]){
            uf.union(row * n + col - n, (row + 1) * n + col - n);
            uf2.union(row * n + col - n, (row + 1) * n + col - n);
        }

    }

    private void east(int row, int col) {
        if (grid[row][col + 1]) {
            uf.union(row * n + col - n, row * n + col + 1 - n);
            uf2.union(row * n + col - n, row * n + col + 1 - n);
        }

    }

    private void north(int row, int col) {
        if (grid[row - 1][col]) {
            uf.union(row * n + col - n, (row - 1) * n + col - n);
            uf2.union(row * n + col - n, (row - 1) * n + col - n);
        }

    }

    private void west(int row, int col) {
        if (grid[row][col - 1]) {
            uf.union(row * n + col - n, row * n + col - 1 - n);
            uf2.union(row * n + col - n, row * n + col - 1 - n);
        }

    }

    public void open(int row, int col) {
        if (isOpen(row, col))
            return;
        grid[row][col] = true;
        if (row == 1) {
            uf.union(0, row * n + col - n);
            uf2.union(0, row * n + col - n);
            if (n == 1) {
                uf.union(length - 1, row * n + col - n);
                count++;
                return;
            }
            if (col == 1) {
                south(row, col);
                east(row, col);
            } else if (col == n) {
                south(row, col);
                west(row, col);
            } else {
                south(row, col);
                east(row, col);
                west(row, col);
            }
        } else if (row == n) {
            uf.union(length - 1, row * n + col - n);
            if (col == 1) {
                north(row, col);
                east(row, col);
            } else if (col == n) {
                north(row, col);
                west(row, col);
            } else {
                north(row, col);
                east(row, col);
                west(row, col);
            }
        } else if (col == 1) {
            north(row, col);
            south(row, col);
            east(row, col);
        } else if (col == n) {
            north(row, col);
            south(row, col);
            west(row, col);
        } else {
            north(row, col);
            south(row, col);
            east(row, col);
            west(row, col);
        }
        count++;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(length - 1);
    }
}
