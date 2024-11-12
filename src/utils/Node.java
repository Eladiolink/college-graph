package utils;

public class Node{
    public int data;
    public Node nextNode;
    public Vertex vertex;
    public Float d;
    public int pi;

    public Node(int data) {
        this.data = data;
        this.nextNode = null;
        this.vertex = null;
        this.d = Float.MAX_VALUE;
    }

    public Node(int data, Float d) {
        this.data = data;
        this.nextNode = null;
        this.vertex = null;
        this.d = d;
    }
}