package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetGraphByFIle {
    public static Grafo gg(String path){
        Grafo grafo = null;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            int count = 0;
            boolean isDigrafo = false;
            int numero_vertices = -1;

            while(line != null){
                if( count == 0) {
                    isDigrafo = Integer.parseInt(line) != 0;
                }
                if( count == 1) numero_vertices = Integer.parseInt(line);
                if(count == 2 ) grafo = new Grafo(isDigrafo, numero_vertices);
                if( count >= 2){
                    String [] values = line.split(" ");
                    int u = Integer.parseInt(values[0]);
                    int v = Integer.parseInt(values[1]);

                    assert grafo != null;

                    if(values.length == 2)
                        grafo.addAresta(u,v);
                    else{
                        Float w = Float.parseFloat(values[values.length - 1]);
                        grafo.addAresta(u,v, w);
                    }
                }
                line = br.readLine();
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i < grafo.listaGrafo.length ; i++){
            if(grafo.listaGrafo[i] == null){
                grafo.listaGrafo[i] = new Node(i);
            }
        }
        return grafo;
    }

    public static Graph readGraph(String path){
        Graph grafo = null;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            int count = 0;
            boolean isDigrafo = false;
            int numero_vertices = -1;

            while(line != null){
                if( count == 0) {
                    isDigrafo = Integer.parseInt(line) != 0;
                }
                if( count == 1) numero_vertices = Integer.parseInt(line);
                if(count == 2 ) grafo = new Graph(isDigrafo, numero_vertices);
                if( count >= 2){
                    String [] values = line.split(" ");
                    int u = Integer.parseInt(values[0]);
                    int v = Integer.parseInt(values[1]);

                    assert grafo != null;

                    if(values.length == 2)
                        grafo.addAresta(u,v);
                    else{
                        Float w = Float.parseFloat(values[values.length - 1]);
                        grafo.addAresta(u,v, w);
                    }
                }
                line = br.readLine();
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i < grafo.GraphList.length ; i++){
            if(grafo.GraphList[i] == null){
                grafo.GraphList[i] = new Node(i);
            }
        }
        return grafo;
    }

}
