import java.util.HashSet;
import java.util.Set;
public class VertexCover {
private static final char[] name_vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
/* // Input Number One
 private static final int[][] matrix = {
 //A B C D E
 { 0, 1, 0, 0, 0 },// A
 { 1, 0, 1, 0, 0 },// B
 { 0, 1, 0, 1, 1 },// C
 { 0, 0, 1, 0, 1 },// D
 { 0, 0, 1, 1, 0 },// E
 };
*/
 // Input Number Two
 // Cormen , Introduction to Algo , Chap 35.1 , Approx Algo , Pg 1109,
 private static final int[][] matrix = {
 //A B C D E F G
 { 0, 1, 0, 0, 0, 0, 0 },// A
 { 1, 0, 1, 0, 0, 0, 0 },// B
 { 0, 1, 0, 1, 1, 0, 0 },// C
 { 0, 0, 1, 0, 1, 1, 1 },// D
 { 0, 0, 1, 1, 0, 1, 0 },// E
 { 0, 0, 0, 1, 1, 0, 0 },// F
 { 0, 0, 0, 1, 0, 0, 0 },// G
 };
 private static final int no_vertices = matrix[0].length;
private static final boolean arr[] = new boolean[no_vertices];
private static void printEnabledVertices(String s) {
 for (int i = 0; i < no_vertices; i++) {
 if (arr[i] == true) { // Vertices chosen for this iteration
 System.out.print(" " + name_vertex[i]);
 }
 }
 System.out.println("");
 pickMinimum();// Written separately :)
 }
private static void checkVertexCover() {
    int count = 0;
    for (int i = 0; i < no_vertices; i++) { // Check the graph Matrix
    for (int j = 0; j < i; j++) {
    if (matrix[i][j] == 1) { // Check this edge
    if (arr[i] || arr[j]) { // u or v or both in cover
    count++;
    } else {
    return; // case u and v don't cover an edge
    }
    }
    }
    }
    if (count > 0) {
    printEnabledVertices(null);
    }
    }
    private static void calcVertexCover(int index) {
        if (index == (-1)) {
        checkVertexCover();
        } else {
        arr[index] = false;
        calcVertexCover(index - 1);
        arr[index] = true;
        calcVertexCover(index - 1);
        }
        }
       public static void main(String args[]) {
        System.out.println("\n\n Vertex Covers Are");
        System.out.println("-----------------------");
        calcVertexCover(no_vertices - 1);
        printMinimum();
        }
       /*CODE TO PICK MINIMUM PLEASE OPTIMIZE BY COMBINING LOOPS */
       private static int min_cover_vertices = no_vertices;
       private static Set<String> min_cover = new HashSet<String>();
       private static String getVertexString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < no_vertices; i++) {
        if (arr[i] == true) { // Vertices chosen for this iteration
        s.append(" " + name_vertex[i]);
    }
}
return s.toString();
}
private static void pickMinimum() { // This function Can be optimized
int count = 0;
for (int i = 0; i < no_vertices; i++) {
if (arr[i] == true) { // Vertices chosen for this iteration
count++;
}
}
if (count > 0) {
if (min_cover_vertices == count) {
min_cover.add(getVertexString());
} else if (min_cover_vertices > count) {
min_cover_vertices = count;
min_cover.clear();
min_cover.add(getVertexString());
} 
}
}
private static void printMinimum() {
if (min_cover.size() > 0) {
System.out.println("\n\n Minimum Covers Are");
System.out.println("-----------------------");
for (String s : min_cover) {
System.out.println(s);
}
}
}
}       