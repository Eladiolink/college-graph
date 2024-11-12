import utils.*;

import java.util.ArrayList;


public class Main{
    public static void main(String[] args) {
        System.out.println("Hello utils.Grafo");

        String dsp = "dijkstra1";
        String fileName = "bf1";
        fileName = dsp;

        Graph G = GetGraphByFIle.readGraph("files/"+fileName+".graph");

        G.printGrafo();
        bellmanFord(G, 0);
        dagShortesPaths(G,0);
        dijkstra(G,1);
    }

    private static void initializeSingleSorce(Graph G,int s){
        for(int i=0;i<G.GraphList.length;i++){
            G.GraphList[i].d = Float.MAX_VALUE;
            G.GraphList[i].pi = -1;
        }

        G.GraphList[s].d = 0.0f;
    }

    private static void relax(Node u, Node v, Float w){
        if( v.d > u.d + w){
            v.d = u.d + w;
            v.pi = u.data;
        }
    }

    private static void relax_dijkstra(Node u, Node v, Float w, Heap h){
        if( v.d > u.d + w){
            h.decreaseKey(v, v.d = u.d + w);
            v.pi = u.data;
        }
    }

    public static void bellmanFord(Graph G, int s){
        initializeSingleSorce(G,s);

        for(int i = 0; i< G.GraphList.length-1 ;i++){
            for(Edge e : G.Edge){
                relax(G.GraphList[e.u],G.GraphList[e.v],e.w);
            }
        }

        for(Edge e : G.Edge){
            if (G.GraphList[e.v].d > G.GraphList[e.u].d){
                break;
            }
        }

        System.out.println("\n\nResumo:");
        for(int i=0; i < G.GraphList.length;i++){
            System.out.println("["+G.GraphList[i].data+"] d: "+G.GraphList[i].d+", Pi:"+G.GraphList[i].pi);
        }

        Main.mountPath(G,s);
    }

    public static void dagShortesPaths(Graph G, int s){
        Queue queueOfGraph = AlgoritmsOfGraph.TopologicalSort(G,false);

        initializeSingleSorce(G,s);

        for(  Node aux= queueOfGraph.queue;aux != null; aux = aux.nextNode){
            int u = aux.data;

            for(Vertex i = G.GraphList[u].vertex; i != null; i= i.nextVertex){
                int v = i.data;
                relax(G.GraphList[u],G.GraphList[v], i.weight);
            }
        }

        queueOfGraph.printQueue();

        System.out.println("\n\nResumo:");
        for(int i=0; i < G.GraphList.length;i++){
            System.out.println("["+G.GraphList[i].data+"] d: "+G.GraphList[i].d+", Pi:"+G.GraphList[i].pi);
        }

        Main.mountPath(G,s);
    }

    public static void dijkstra(Graph G, int s){

        initializeSingleSorce(G,s);
        ArrayList<Node> S = new ArrayList<>();

        Heap h = new Heap(G.GraphList.length);

        for(Node n: G.GraphList){
            h.insert(n);
        }

        while(h.size > 0){
            Node u = h.extractMin();
            S.add(u);

            for(Vertex v= u.vertex; v!=null; v = v.nextVertex){
                relax_dijkstra(G.GraphList[u.data],G.GraphList[v.data],v.weight, h);
                // TODO adicionar o Decrease key
            }
        }

        System.out.println("\n\nResumo:");
        for(int i=0; i < G.GraphList.length;i++){
            System.out.println("["+G.GraphList[i].data+"] d: "+G.GraphList[i].d+", Pi:"+G.GraphList[i].pi);
        }

            Main.mountPath(G,s);
    }

    public static void mountPath(Graph G, int s){
        Stack path;
        System.out.println();
        for(int i=0; i<G.GraphList.length;i++){
            if(i==s) continue;
            path = new Stack();
            Node n = G.GraphList[i];

            while (n.pi != -1){
                path.push(n.data);
                n = G.GraphList[n.pi];
            }

            System.out.print("Path ["+s+"] to ["+i+"]: "+s+" ~> ");
            while (path.getTop() != null) {
                int elm = path.pop();
                if(elm!=i)
                    System.out.print(elm+ " ~> ");
                else
                    System.out.println(elm);
            }
        }
    }

}

