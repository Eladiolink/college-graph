package utils;

public class Vertex {
    public Float weight;
    public int data;
    public Vertex nextVertex = null;

    public Vertex(int data) {
        this.data = data;
        this.nextVertex = null;
    }
}
