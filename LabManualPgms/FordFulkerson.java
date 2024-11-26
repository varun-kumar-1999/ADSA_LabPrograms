import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class FordFulkerson{
    private int[] parent;
    private Queue<Integer> queue;
    private int n;
    private boolean[] vis;
    public FordFulkerson(int n){
        this.n = n;
        this.queue = new LinkedList<Integer>();
        parent = new int[n + 1];
        vis = new boolean[n + 1];
    }
    public boolean bfs(int src, int goal, int graph[][]){
        boolean pathFound = false;
        int dest, element;
        for(int i = 1; i <= n; i++){
            parent[i] = -1;
            vis[i] = false;
        }
        queue.add(src);
        parent[src] = -1;
        vis[src] = true;
        while (!queue.isEmpty()){ 
            element = queue.remove();
            dest = 1;
            while (dest <= n){
                if (graph[element][dest] > 0 && !vis[dest])
                {
                    parent[dest] = element;
                    queue.add(dest);
                    vis[dest] = true;
                }
                dest++;
            }
        }
        if(vis[goal]){
            pathFound = true;
        }
        return pathFound;
    }
public int fordFulkerson(int graph[][], int src, int dest)
 {
 int u, v;
 int maxFlow = 0;
 int pathFlow;
 int[][] rgraph = new int[n + 1][n + 1];
 for (int i = 1; i <= n; i++){
    for (int j = 1; j <= n; j++){
        rgraph[i][j] = graph[i][j];
    }
}
 while (bfs(src ,dest, rgraph))
 {
 pathFlow = Integer.MAX_VALUE;
 for (v = dest; v != src; v = parent[v])
 {
 u = parent[v];
 pathFlow = Math.min(pathFlow, rgraph[u][v]);
 }
 for (v = dest; v != src; v = parent[v])
 {
 u = parent[v];
 rgraph[u][v] -= pathFlow;
 rgraph[v][u] += pathFlow;
 }
 maxFlow += pathFlow;
 }
 return maxFlow;
}
    public static void main(String...arg){
        int[][] graph;
        int numberOfNodes,src,sink,maxFlow;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of nodes");
        numberOfNodes = scanner.nextInt();
        graph = new int[numberOfNodes + 1][numberOfNodes + 1];
        System.out.println("Enter the graph matrix");
        for (int i = 1; i <= numberOfNodes; i++){
            for (int j = 1; j <= numberOfNodes; j++){
                graph[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter the src of the graph");
        src= scanner.nextInt();
        System.out.println("Enter the sink of the graph");
        sink = scanner.nextInt();
        
        FordFulkerson fordFulkerson = new FordFulkerson(numberOfNodes);
        maxFlow = fordFulkerson.fordFulkerson(graph, src, sink);
        System.out.println("The Max Flow is " + maxFlow);
        scanner.close();
    }
}