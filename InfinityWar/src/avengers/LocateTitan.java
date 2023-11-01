package avengers;
/**
 * 
 * Using the Adjacency m of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency m for g generators.
 * 
 * Populate the adjacency m with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency m to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency m representing the TOTAL costS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, mincost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(mincost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
    StdIn.setFile(args[0]);
    StdOut.setFile(args[1]);


    int numGen = StdIn.readInt();
    int[] genNum = new int[numGen];
    double[] funcVal = new double[numGen];
    int[][] adjM = new int[numGen][numGen];

    for (int i=0; i < numGen; i++){
        genNum[i] = StdIn.readInt();
        funcVal[i] = StdIn.readDouble();
    }
    for(int i=0; i < numGen; i++){
        for (int j = 0; j < numGen; j++){
            adjM[i][j] = (int)(StdIn.readInt() / (funcVal[i]*funcVal[j]));
        }
    }
    

    int[] mincost = dijkstra(adjM);
    StdOut.print(mincost[numGen-1]);
}

    private static int[] dijkstra(int[][] m){
        int[] cost = new int[m.length];
        boolean[] dijkstraSet = new boolean[m.length];

        for (int i=1; i < cost.length; i++){
            cost[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < cost.length; i++) {
            int currS = getMin(cost, dijkstraSet);
            dijkstraSet[currS] = true;
            for (int j = 0; j < m.length; j++){
                int sum = cost[currS] + m[currS][j];
                if (m[currS][j] != 0 && !dijkstraSet[j] && sum < cost[j]) {
                    cost[j] = sum;
                    }
                }
        }  
        return cost;  
    }
    private static int getMin (int[] mc, boolean[] ds) {
        int min = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < mc.length; i++) {
            if (!ds[i] && mc[i] < min) {
                min = mc[i];
                minNode = i;
            }
        }
        return minNode;
    }

}   
