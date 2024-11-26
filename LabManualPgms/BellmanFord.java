import java.util.Scanner;
public class BellmanFord
{
    private int distance[],n;
    public static final int MAX_VALUE=999;
    public BellmanFord(int n){
        this.n = n;
        distance = new int[n+1];
    }
    public void BellmanFordEvaluation(int source,int adj[][]){
        for(int i=1;i<=n;i++){
            distance[i]=MAX_VALUE;
        }
        distance[source]=0;
        for(int node=1;node<=n-1;node++){
            for(int src=1;src<=n;src++){
                for(int dest=1;dest<=n;dest++){
                    if(adj[src][dest] != MAX_VALUE){
                        if(distance[dest] > distance[src] + adj[src][dest])
                            distance[dest] = distance[src] + adj[src][dest];
                        }
                    }
                }
            }
            
        for(int src=1;src<=n;src++){
            for(int dest=1;dest<=n;dest++){
                if(adj[src][dest] != MAX_VALUE){
                    if(distance[dest] > distance[src] + adj[src][dest])
                        System.out.println("The graph contains negative edge cycle");
                    }
                }
            }
        for(int vertex=1;vertex<=n;vertex++){
            System.out.println("distance of source "+source+" to "+vertex+" is "+distance[vertex]);
        }
    }
    public static void main(String args[]){
        int n,source;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        n = scanner.nextInt();
        int adj[][]=new int[n+1][n+1];
        System.out.println("Enter the adjacency matrix");
        for(int src=1;src<=n;src++){
            for(int dest=1;dest<=n;dest++){
                adj[src][dest]=scanner.nextInt();
                if(src==dest){
                    adj[src][dest]=0;
                    continue;
                }
                if(adj[src][dest]==0){
                    adj[src][dest]=MAX_VALUE;
                }
            }
        }
        System.out.println("Enter the source vertex");
        source=scanner.nextInt();
        BellmanFord bellmanford=new BellmanFord(n);
        bellmanford.BellmanFordEvaluation(source,adj);
        scanner.close();
    }
}