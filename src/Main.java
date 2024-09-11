import utils.Color;
import utils.Node;
import utils.Queue;

public class Main{
    private static int tempo = 0;
    public static void main(String[] args) {
        System.out.println("Hello Grafo");

        Grafo HelloGrafo = new Grafo(false,10);

//        HelloGrafo.addAresta(0,4);
        HelloGrafo.addAresta(1,4);
        HelloGrafo.addAresta(1,3);
        HelloGrafo.addAresta(1,2);
        HelloGrafo.addAresta(3,1);

        Node AUX = HelloGrafo.listaGrafo[0];


//        HelloGrafo.printGrafo();
//          bfs(HelloGrafo.listaGrafo,1);
          dfs(HelloGrafo.listaGrafo);
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
        Integer tempoInicio[] = new Integer[G.length];
        Integer tempoFim[] = new Integer[G.length];
        Integer pai[] = new Integer[G.length];

        Main.tempo = 0;
//        int distancia[] = new int[G.length];

        for(int i=0; i< G.length ;i++) {
            cor[i] = Color.BRANCO;
            tempoInicio[i] = -1;
            tempoFim[i] = -1;
            pai[i] = -1;
        }

        for(int i=0; i< G.length ;i++) {
            if (cor[i] == Color.BRANCO) dfs_visit(G, i, cor, tempoInicio, tempoFim, pai);
        }


        for(int i=0; i<G.length ; i++)
            System.out.println("["+i+"]"+" - Tempo Descoberta: "+tempoInicio[i]+", Tempo Finalização: "+tempoFim[i]+", Pai: "+pai[i]);
    }

    private static void dfs_visit(Node[] G, int s, Color[] cor,Integer [] tempoInicio, Integer [] tempoFim, Integer [] pai){

        tempo++;
        tempoInicio[s] =  Main.tempo;
        cor[s] = Color.CINZA;

        Node AUX = G[s];

        while (AUX != null){
            if ( cor[AUX.data] == Color.BRANCO ){
                pai[AUX.data] = s;

                dfs_visit(G,AUX.data,cor, tempoInicio, tempoFim, pai);
            }
            AUX = AUX.nextNode;
        }

        cor[s] = Color.PRETO;
        Main.tempo += 1;
        tempoFim[s] =  Main.tempo;
    }
}

