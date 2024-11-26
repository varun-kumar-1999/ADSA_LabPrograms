import java.io.*;
import java.util.Set;
import java.util.HashSet;
/**
* Implementation of maximum bipartite matching algorithm. Transcribe the labeling-flipping
* implementation of augumenting-path algorithm on the adjacency matrix representation of
* graph. Current implementation assumes undirected, bipartite graph.
*/
public class MaxBipartite {
 int adjacency [][]; // the adjacency matrix of the graph. 1=edge, 0=no edge.
 private final int MATCH = 2; // 1* in original algorithm
 private final int NOT_MATCH = 1;
 private final int NOT_LABELED = -1;
 private final int POUND_LABELED = -2; // # label in original algorithm
 private int rows;
 private int cols;
 private int colLabel []; // labeling flag of columns
 private int rowLabel []; // labeling flag of rows
 private boolean colScan []; // scaning flag of columns
 private boolean rowScan []; // scaning flag of rows
 /**
 * Constructor given the adjacency matrix in "reduced" form. 
 */
public MaxBipartite(int adj [][]) {
    rows = adj.length;
    cols = adj[0].length;
    adjacency = new int [rows][cols];
    // make an identical copy of the given adjacency matrix
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++)
    adjacency [i][j] = adj [i][j];
    }
    colLabel = new int [cols];
    rowLabel = new int [rows];
    colScan = new boolean [cols];
    rowScan = new boolean [rows];
    }
    public MaxBipartite (double adj [][]) {
        rows = adj.length;
        cols = adj[0].length;
        adjacency = new int [rows][cols];
        // convert the given ajdacency matrix of double to an upper triangle adjacency matrix of int.
        for (int i = 0; i < rows; i++) {
        for (int j = i+1; j < cols; j++) {
        if (adj [i][j] == 1.0)
        adjacency [i][j] = NOT_MATCH;
        }
        }
        colLabel = new int [cols];
        rowLabel = new int [rows];
        colScan = new boolean [cols];
        rowScan = new boolean [rows];
        }
        public void match () {
            // the accumulating set of column IDs of "1" already selected into the initial matching.
            Set usedIdx = new HashSet ();
            for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            if (adjacency [i][j] == NOT_MATCH) {
            String idx = String.valueOf (j);
            if (!usedIdx.contains (idx)) {
            adjacency [i][j] = MATCH;
            usedIdx.add (idx);
            break;// only one needed for each row, break out to next row.
            }
            }
            }
            }
            resetFlags ();
            label ();
            }
            protected void label () {
                // column scanning
                boolean colScanDone = true;// flow control flag. true = no column exists as labeled but not scanned
                for (int i = 0; i < cols; i++) {
                if (colLabel [i] != NOT_LABELED && !colScan [i]) {
                colScanDone = false;
                for (int j = 0; j < rows; j++) {
                if (adjacency [j][i] == NOT_MATCH && rowLabel [j] == NOT_LABELED)
                rowLabel [j] = i; 
                }
                colScan [i] = true;
                }
                }
                // row scanning
 boolean rowScanDone = true;// flow control flag, similar to colScanDone.
 int freeRow = -1;
 for (int i = 0; i < rows; i++) {
    if (rowLabel [i] != NOT_LABELED && !rowScan [i]) {
    rowScanDone = false;
    boolean foundMatch = false;// flag to remember if 1* is found in this row
    for (int j = 0; j < cols; j++) {
    if (adjacency [i][j] == MATCH) {
    colLabel [j] = i;
    foundMatch = true;
    break;// found 1*, exit from this row scaning
    }
    }
    rowScan [i] = true;
    if (!foundMatch) {// no 1* found in this row, should (prematurely) exit from labeling  phase and go to flipping phase
    freeRow = i;
    break;
    }
    }
    }
    if (freeRow != -1)
 flipping (freeRow);// go to flipping phase
 else {
 if (colScanDone && rowScanDone) // algorithm finished
 return;
 else
 label();
}
}
/**
* Flipping phase
*/
protected void flipping (int freeRow) {
int c = rowLabel [freeRow];
adjacency [freeRow][c] = MATCH;
int r = colLabel [c];
if (r == POUND_LABELED) {// # labeled, go back to labeling phase with all flags reset.
resetFlags ();
label ();
}
else {// otherwise, recursively flip the labeled row
adjacency [r][c] = NOT_MATCH;
flipping (r);
}
}
protected void resetFlags () {
    // reset row scan/label flags
    for (int i = 0; i < rows; i++) {
    rowLabel [i] = NOT_LABELED;
    rowScan [i] = false; 
    }
    // reset column scan/label flags
    for (int i = 0; i < cols; i++) {
    colScan [i] = false;
    colLabel [i] = POUND_LABELED;// labeled # as default
    for (int j = 0; j < rows; j++) {
    if (adjacency [j][i] == MATCH) {// find 1* (match), remove label
    colLabel [i] = NOT_LABELED; 
    break;
    }
    }
    }
    }
    public int [][] getAdj () {
        return adjacency;
        }
        /**
        * Utility function. Post process the adjacency matrix and return the matching number |M|.
        */ 
        public int countMatch () {
        System.err.println ("The maximum matching is as follows:");
        int count = 0;
        for (int i = 0; i< rows; i++) {
        for (int j = 0; j< cols; j++) {
        if (adjacency[i][j] == MATCH) {
        count ++;
        System.err.println ("("+i+","+j+")");
        }
        }
        }
        return count;
        }
        public static void testCase (int adj [][]) {
            MaxBipartite mbm = new MaxBipartite (adj);
            mbm.match ();
            System.out.println ("Matching Number = " + mbm.countMatch ());
            System.out.println ("Matching matrix:");
            int match[][] = mbm.getAdj ();
            int rows = match.length;
            int cols = match[0].length;
            System.out.println ("\t0\t1\t2");
            System.out.println ("\t_\t_\t_");
            for (int i = 0; i < rows; i++) {
            System.out.print (i+"|\t");
            for (int j = 0; j < cols; j++)
            System.out.print (match [i][j] + "\t");
            System.out.println ();
            }
            }
            public static void main(String args []) throws Exception {
                System.out.println("The adjacency matrix are ");
                int adj[][] = {{1,1,1}, {1,0,0}, {1,0,1}};
                for(int i=0;i<adj.length;i++)
                {
                System.out.println("\n");
                for(int j=0;j<adj.length;j++)
                System.out.print(adj[i][j]+" \t");
                }
                
                System.out.println("\n");
                testCase (adj);
                }
                
               }               