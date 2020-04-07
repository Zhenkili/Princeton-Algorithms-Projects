/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private double[] res;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.res = new double[trials];
        for(int i=0; i< trials; i++){
            Percolation perco = new Percolation(n);
            while(!perco.percolates()){
                perco.open(StdRandom.uniform(1,n+1), StdRandom.uniform(1, n+1));
            }
            res[i] = (double) perco.numberOfOpenSites() / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96*stddev()/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96*stddev()/Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args){

        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}