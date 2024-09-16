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
                    if(Integer.parseInt(line) == 0 ) isDigrafo = false;
                    else isDigrafo = true;
                }
                if( count == 1) numero_vertices = Integer.parseInt(line);
                if(count == 2 ) grafo = new Grafo(isDigrafo, numero_vertices);
                if( count >= 2){
                    String [] values = line.split(" ");
                    int u = Integer.parseInt(values[0]);
                    int v = Integer.parseInt(values[1]);
                    grafo.addAresta(u,v);
                }
                line = br.readLine();
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return grafo;
    }
}
