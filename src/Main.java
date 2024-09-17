import utils.*;


public class Main{
    private static int tempo = 0;
    public static void main(String[] args) {
        System.out.println("Hello utils.Grafo");

        Grafo OkGrafo = GetGraphByFIle.gg("1.txt");

        OkGrafo.printGrafo();

//          bfs(OkGrafo.listaGrafo,0);
//          dfs(OkGrafo.listaGrafo);
//          TopologicalSort(OkGrafo.listaGrafo);
            componentes_fortemente_conexas(OkGrafo);
    }

    public static void bfs(Node[] G, int s){
        System.out.println();
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
        System.out.println("Vertice "+s+", Cor Alterada para: "+Color.CINZA);

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
                    System.out.println("Vertice "+elm+", Cor Alterada para: "+Color.CINZA);

                    distancia[elm] = distancia[u.data] + 1;
                    pai[elm] = u.data;
                    fila.enqueue(elm);
                }
            }

            cor[u.data] = Color.PRETO;
            System.out.println("Vertice "+u.data+", Cor Alterada para: "+Color.PRETO);

        }
        resumo(cor, distancia, pai, s, G.length);
    }

    public static void dfs(Node[] G){
        System.out.println();
        Color cor[] = new Color[G.length];
        Integer tempoInicio[] = new Integer[G.length];
        Integer tempoFim[] = new Integer[G.length];
        Integer pai[] = new Integer[G.length];

        Main.tempo = 0;

        for(int i=0; i< G.length ;i++) {
            cor[i] = Color.BRANCO;
            tempoInicio[i] = -1;
            tempoFim[i] = -1;
            pai[i] = -1;
        }

        for(int i=0; i< G.length ;i++)
            if (cor[i] == Color.BRANCO) dfs_visit(G, i, cor, tempoInicio, tempoFim, pai);

        System.out.println("\nResumo fim da Execução");

        System.out.println("COR:");
        for(int i=0;i< G.length; i++)
            System.out.println("Cor vertice "+i+" : "+ cor[i]);

        System.out.println("\nTempo de Descoberta, Finalização e Pai:");
        for(int i=0; i<G.length ; i++)
            System.out.println("["+i+"]"+" - Tempo Descoberta: "+tempoInicio[i]+", Tempo Finalização: "+tempoFim[i]+", Pai: "+pai[i]);

    }

    private static void dfs_visit(Node[] G, int s, Color[] cor,Integer [] tempoInicio, Integer [] tempoFim, Integer [] pai){

        tempo++;
        tempoInicio[s] =  Main.tempo;
        cor[s] = Color.CINZA;

        System.out.println("Vertice "+s+", Cor Alterada para: "+Color.CINZA);

        Node AUX = G[s];

        while (AUX != null){
            if ( cor[AUX.data] == Color.BRANCO ){
                pai[AUX.data] = s;

                dfs_visit(G,AUX.data,cor, tempoInicio, tempoFim, pai);
            }
            AUX = AUX.nextNode;
        }

        cor[s] = Color.PRETO;
        System.out.println("Vertice "+s+", Cor Alterada para: "+Color.PRETO);
        Main.tempo += 1;
        tempoFim[s] =  Main.tempo;
    }

    private static Queue TopologicalSort(Node [] G, boolean resume){
        Color cor[] = new Color[G.length];
        Integer tempoInicio[] = new Integer[G.length];
        Integer tempoFim[] = new Integer[G.length];
        Integer pai[] = new Integer[G.length];
        Main.tempo = 0;

        for(int i=0; i< G.length ;i++) {
            cor[i] = Color.BRANCO;
            tempoInicio[i] = -1;
            tempoFim[i] = -1;
            pai[i] = -1;
        }

        Queue q = new Queue();
        for(int i=0; i< G.length ;i++) {
            if (cor[i] == Color.BRANCO) TS_Dfs_visit(G, i, cor, tempoInicio, tempoFim, pai, q);
        }

        if(resume) {
            System.out.println("\nResumo fim da Execução");

            System.out.println("COR:");
            for (int i = 0; i < G.length; i++)
                System.out.println("Cor vertice " + i + " : " + cor[i]);

            System.out.println("\nTempo de Descoberta, Finalização e Pai:");
            for (int i = 0; i < G.length; i++)
                System.out.println("[" + i + "]" + " - Tempo Descoberta: " + tempoInicio[i] + ", Tempo Finalização: " + tempoFim[i] + " | Pai:  " + pai[i]);

            System.out.println("\nOrdem Topologica:");
            q.printQueue();
        }
        return  q;
    }

    private static void TS_Dfs_visit(Node[] G, int s, Color[] cor,Integer [] tempoInicio, Integer [] tempoFim, Integer [] pai, Queue q){

        tempo++;
        tempoInicio[s] =  Main.tempo;
        cor[s] = Color.CINZA;

        Node AUX = G[s];

        while (AUX != null){
            if ( cor[AUX.data] == Color.BRANCO ) {
                pai[AUX.data] = s;
                TS_Dfs_visit(G,AUX.data,cor, tempoInicio, tempoFim, pai, q);
            }

            AUX = AUX.nextNode;
        }

        cor[s] = Color.PRETO;
        Main.tempo += 1;
        tempoFim[s] =  Main.tempo;
        q.enqueueFront(s);
    }

    private static void resumo(Color [] cor, int [] distancia, int [] pai, int s, int length){

        System.out.println("\nResumo fim da Execução");

        System.out.println("COR:");
        for(int i=0;i< length; i++){
            System.out.println("Cor vertice "+i+" : "+ cor[i]);
        }

        System.out.println("\nDistancia do vertice "+s+":");
        for(int i=0;i< length; i++){
            System.out.println("Distancia "+i+" : "+ distancia[i]);
        }

        System.out.println("\nPai: ");
        for(int i=0;i< length; i++){
            System.out.println("Pai de "+i+" : "+ pai[i]);
        }
    }

    private static void componentes_fortemente_conexas(Grafo G){
        Grafo transpostor = G.grafoTransposto();

        System.out.println("\nGrafo Transposto:");
        transpostor.printGrafo();

        Queue finalizacoes = Main.TopologicalSort(G.listaGrafo,false);

        System.out.println();
        Node [] lista = transpostor.listaGrafo;
        Color cor[] = new Color[lista.length];
        Integer tempoInicio[] = new Integer[lista.length];
        Integer tempoFim[] = new Integer[lista.length];
        Integer pai[] = new Integer[lista.length];

        for(int i=0; i< lista.length ;i++) {
            cor[i] = Color.BRANCO;
            tempoInicio[i] = -1;
            tempoFim[i] = -1;
            pai[i] = -1;
        }

        System.out.println("Componentes Fortemente Conexa:");
        for(Node i = finalizacoes.queue; i!= null ; i = i.nextNode){
            Queue conexas = new Queue();
            if (cor[i.data] == Color.BRANCO) TS_Dfs_visit(lista, i.data, cor, tempoInicio, tempoFim, pai, conexas);

            if (conexas.queue != null)
                conexas.printQueue();
        }

//        finalizacoes.printQueue();
    }
}

