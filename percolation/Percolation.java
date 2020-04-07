/* *****************************************************************************
 *  Name: Zhenqi
 *  Date: 2020/4/07
 *  Description: My first homework
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};
    private int closed;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        grid = new int[n][n];
        this.n = n;
        closed = n*n;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = 0;
            }
        }

        uf = new WeightedQuickUnionUF(n*n+2); //one more for the final check

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!isOpen(row, col)){
            grid[row-1][col-1] = 1; //open the gate!
            closed--;
            if(row == 1){
                uf.union(0, toNum(row, col));
            }
            if(row == n){
                uf.union(n*n+1, toNum(row, col));
            }
            for(int k=0; k<4; k++){ //check if it has water!
                int nrow = row + dx[k];
                int ncol = col + dy[k];

                if(nrow>n || ncol>n || nrow<1 || ncol<1 || grid[nrow-1][ncol-1]==0){
                    continue;
                }
                if(grid[nrow-1][ncol-1] == 2){
                    uf.union(0, toNum(row, col));
                }
                uf.union(toNum(row, col), toNum(nrow, ncol));
            }
            return;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int i = row-1;
        int j = col-1;
        if(i<0 || j<0 || i>=n || j>=n){
            throw new IllegalArgumentException();
        }
        return grid[i][j]==1 || grid[i][j]==2;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<1 || col<1 || row>n || col>n){
            throw new IllegalArgumentException();
        }
        return uf.connected(0, toNum(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return n*n - closed;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(0, n*n+1);
    }

    private int toNum(int i, int j){
        return (i-1)*n + j;
    }

    // test client (optional)
    public static void main(String[] args){
    }
}