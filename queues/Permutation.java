/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> random = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()){
            random.enqueue(StdIn.readString());
        }
        for(int i = 0; i < num; i++){
            StdOut.println(random.dequeue());
        }
    }
}
