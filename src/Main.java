import utils.Color;
import utils.Node;
import utils.Queue;

public class Main{
    private static int tempo = 0;
    public static void main(String[] args) {
        System.out.println("Hello Grafo");

        Grafo HelloGrafo = new Grafo(true,10);
        HelloGrafo.addAresta(1,4);
        HelloGrafo.addAresta(1,3);
        HelloGrafo.addAresta(1,2);
        HelloGrafo.addAresta(3,1);

//        HelloGrafo.printGrafo();
          bfs(HelloGrafo.listaGrafo,1);
    }

    public static void bfs(Node[] G, int s){
        Color cor[] = new Color[G.length];
        int pai[] = new int[G.length];
        int distancia[] = new int[G.length];

        for(int i=0; i< G.length ;i++){
            if(i == s) continue;
            cor[i] = Color.BRANCO;
            pai[i] = -1;
            distancia[i] = -1;
        }

        cor[s] = Color.CINZA;
        pai[s] = -1;
        distancia[s] = 0;

        Queue fila = new Queue();
        fila.enqueue(s);

        while(fila.queue != null){
            Node u = fila.denqueue();

            for(Node i = G[u.data]; i != null; i = i.nextNode){
                int elm = i.data;
                if(cor[elm] == Color.BRANCO){
                    cor[elm] = Color.CINZA;
                    distancia[elm] = distancia[u.data] + 1;
                    pai[elm] = pai[u.data];
                    fila.enqueue(elm);
                }
            }

            cor[u.data] = Color.PRETO;
        }
    }

    public static void dfs(Node[] G){
        Color cor[] = new Color[G.length];
        int pai[] = new int[G.length];
        int distancia[] = new int[G.length];

        for(int i=0; i< G.length ;i++){
            cor[i] = Color.BRANCO;
            pai[i] = -1;
            distancia[i] = -1;
        }
    }

    private static void dfs_visit(Node[] G, int s){}
}

