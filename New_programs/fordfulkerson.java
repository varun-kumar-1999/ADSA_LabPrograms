import java.lang.*;
import java.util.*;

public class fordfulkerson {
    public static int n;
    boolean bfs(int[][] rgraph,int s,int t,int[]parent){
        boolean []vis = new boolean[n+1];
        Arrays.fill(vis,false);
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        vis[s] = true;
        parent[s] = -1;
        while(!q.isEmpty()){
            int u = q.poll();
            for(int i = 1;i<=n;i++){
                if(!vis[i] && rgraph[u][i] > 0){
                    q.add(i);
                    parent[i] = u;
                    vis[i] = true;
                    
                }
            }
        }
        return vis[t];
    }
    int fordfulk(int[][]graph,int s,int t){
        int u,v,maxflow = 0;
        int []parent = new int[n+1];
        int [][]rgraph = new int[n+1][n+1];
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                rgraph[i][j] = graph[i][j];
            }
        }

        while(bfs(rgraph,s,t,parent)){
            int pathflow = Integer.MAX_VALUE;
            for(v = t;v != s;v = parent[v]){
                u = parent[v];
                pathflow  = Math.min(pathflow,rgraph[u][v]);
            }
            for(v = t;v != s;v = parent[v]){
                u = parent[v];
                rgraph[u][v] -= pathflow;
                rgraph[v][u] += pathflow;
            }
            maxflow += pathflow;
        }

        return maxflow;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no. of nodes: ");
        n = s.nextInt();
        int [][]graph = new int[n+1][n+1];
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                graph[i][j] = s.nextInt();
            }
        }
        System.out.println("Enter source: ");
        int src = s.nextInt();
        System.out.println("Enter sink: ");
        int dest = s.nextInt();

        fordfulkerson ff = new fordfulkerson();
        int mxflow = ff.fordfulk(graph, src, dest);
        System.out.println("Maximum flow: " +mxflow);
    }
}
