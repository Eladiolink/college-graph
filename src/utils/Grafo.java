package utils;

public class Grafo{
    private boolean isDigrafo;
    public Node[] listaGrafo;
    public Integer[][] matrizGrafo;
    private int qtdVertices;
    private boolean isLinkedList;

    public Grafo(boolean digrafo,int qtdVertices, boolean isLinkedList){
        this.isDigrafo = digrafo;

        if(isLinkedList){
            this.listaGrafo =  new Node[qtdVertices];
            for(Node elm : listaGrafo) elm = null;
        }else{
            this.matrizGrafo = new Integer[qtdVertices][qtdVertices];
            for(int i=0;i<qtdVertices;i++){
                for(int j=0;j<qtdVertices;j++) this.matrizGrafo[i][j] = null;
            }
        }

        this.isLinkedList = isLinkedList;
        this.qtdVertices = qtdVertices;

    }

    public Grafo(boolean digrafo,int qtdVertices){
        this.isDigrafo = digrafo;

        this.listaGrafo =  new Node[qtdVertices];
        for(Node elm : listaGrafo) elm = null;

        this.isLinkedList = true;
        this.qtdVertices = qtdVertices;

    }

    public boolean addAresta(int u,int v){
        boolean res = false;
        if(this.isLinkedList){
            res = addArestaLinkedList(u,v);
        }else{
            res = addArestaMatriz(u,v);
        }

        return res;
    }

    public boolean addArestaMatriz(int u,int v){
        if(this.matrizGrafo[u][v] != null) return false;

        if(isDigrafo){
            this.matrizGrafo[u][v] = 1;
        }else{
            this.matrizGrafo[u][v] = 1;
            this.matrizGrafo[v][u] = 1;
        }

        return true;
    }

    public boolean addArestaLinkedList(int u,int v){

        Node aux = this.listaGrafo[u];

        while(aux != null){
            if(aux.data == v) return false;
            aux = aux.nextNode;
        }

        if(isDigrafo){
            Node elmNew = new Node(v);

            if(listaGrafo[u] == null){
                listaGrafo[u] = elmNew;
                return true;
            }

            elmNew.nextNode = listaGrafo[u];
            listaGrafo[u] = elmNew;
        }else{
            Node elmNewV = new Node(v);

            if(listaGrafo[u] == null){
                listaGrafo[u] = elmNewV;
            }else{
                elmNewV.nextNode = listaGrafo[u];
                listaGrafo[u] = elmNewV;
            }

            Node elmNewU = new Node(u);

            if(listaGrafo[v] == null){
                listaGrafo[v] = elmNewU;
            }else{
                elmNewU.nextNode = listaGrafo[v];
                listaGrafo[v] = elmNewU;
            }
        }

        return true;
    }

    public void printGrafo(){
        if(this.isLinkedList)
            printGrafoLinkedList();
        else
            printGrafoMatriz();
    }

    public void printGrafoMatriz() {
        System.out.print("   ");
        for(int i=0;i<this.qtdVertices;i++) System.out.print(+i+" | ");
        System.out.println();

        int count = 0;
        for(int i=0;i<this.qtdVertices;i++){
            System.out.print(count+"| ");
            for(int j=0;j<this.qtdVertices;j++){
                if(this.matrizGrafo[i][j] != null)
                    System.out.print(1+"   ");
                else
                    System.out.print(0+"   ");
            }
            System.out.println();
            count++;
        }
    }

    public void printGrafoLinkedList(){
        for(int i = 0; i<qtdVertices ;i++){
            System.out.print("["+i+"]: -> ");

            if(listaGrafo[i] != null){

                Node tmp = listaGrafo[i];

                while(tmp != null){
                    System.out.print(tmp.data+" -> ");
                    tmp = tmp.nextNode;

                }
            }

            System.out.print("null");

            System.out.println();
        }
    }
}