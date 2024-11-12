package utils;

import java.util.ArrayList;

public class Graph {
    private boolean isDigrafo;
    public Node[] GraphList;
    public ArrayList<Edge> Edge = new ArrayList<>();
    private int qtdVertices;
    private boolean isLinkedList;

    public Graph(boolean digrafo, int qtdVertices, boolean isLinkedList){
        this.isDigrafo = digrafo;

        this.GraphList =  new Node[qtdVertices];
        for(int elm =0; elm < GraphList.length; elm++){
            GraphList[elm] = new Node(elm);
        }

        this.isLinkedList = isLinkedList;
        this.qtdVertices = qtdVertices;

    }

    public Graph(boolean digrafo, int qtdVertices){
        this.isDigrafo = digrafo;

        this.GraphList =  new Node[qtdVertices];
        for(int elm =0; elm<GraphList.length; elm++) {
            GraphList[elm] = new Node(elm);
        }

        this.isLinkedList = true;
        this.qtdVertices = qtdVertices;

    }

    public boolean addAresta(int u,int v){
        boolean res = false;

        res = addArestaLinkedList(u,v);

        return res;
    }

    public boolean addAresta(int u,int v, Float weight){
        boolean res = false;

        res = addArestaLinkedList(u,v, weight);
        this.Edge.add(new Edge(u,v,weight));
        return res;
    }

    public boolean addArestaLinkedList(int u,int v){

        Node aux = this.GraphList[u];

        while(aux != null){
            if(aux.data == v) return false;
            aux = aux.nextNode;
        }

        if(isDigrafo){
            Node elmNew = new Node(v);

            if(GraphList[u] == null){
                GraphList[u] = elmNew;
                return true;
            }

            elmNew.nextNode = GraphList[u];
            GraphList[u] = elmNew;
        }else{
            Node elmNewV = new Node(v);

            if(GraphList[u] == null){
                GraphList[u] = elmNewV;
            }else{
                elmNewV.nextNode = GraphList[u];
                GraphList[u] = elmNewV;
            }

            Node elmNewU = new Node(u);

            if(GraphList[v] == null){
                GraphList[v] = elmNewU;
            }else{
                elmNewU.nextNode = GraphList[v];
                GraphList[v] = elmNewU;
            }
        }

        return true;
    }

    public boolean addArestaLinkedList(int u,int v, Float weight){

        Vertex aux = this.GraphList[u].vertex;

        while(aux != null){
            if(aux.data == v) return false;
            aux = aux.nextVertex;
        }

        if(isDigrafo){
            Vertex elmNew = new Vertex(v);

            elmNew.weight = weight;
            elmNew.nextVertex = GraphList[u].vertex;
            GraphList[u].vertex = elmNew;
        }else{
            Vertex elmNewV = new Vertex(v);

            elmNewV.weight = weight;
            elmNewV.nextVertex = GraphList[u].vertex;
            GraphList[u].vertex = elmNewV;

            Vertex elmNewU = new Vertex(u);

            elmNewU.weight = weight;
            elmNewU.nextVertex = GraphList[v].vertex;
            GraphList[v].vertex = elmNewU;
        }

        return true;
    }

    public void printGrafo(){
        for(int i = 0; i<qtdVertices ;i++){
            System.out.print("["+i+"]: -> ");

            if(GraphList[i] != null){

                Vertex tmp = GraphList[i].vertex;

                while(tmp != null){
                    System.out.print(tmp.data+" -> ");
                    tmp = tmp.nextVertex;

                }
            }

            System.out.print("null");

            System.out.println();
        }
    }

    public Graph grafoTransposto(){
        Graph transposto = new Graph(this.isDigrafo,this.qtdVertices,this.isLinkedList);

        for(int i =0; i<this.GraphList.length ; i++){
            for(Node j = this.GraphList[i]; j != null; j = j.nextNode){
                transposto.addAresta(j.data,i);
            }
        }
        return transposto;
    }
}