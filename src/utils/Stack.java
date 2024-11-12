package utils;


class NodeOfStack{
    public int elm;
    public NodeOfStack preventElement;

    public NodeOfStack(int elm){
        this.elm = elm;
        preventElement = null;
    }
}
public class Stack {
    NodeOfStack top;

    public Stack(){
        top = null;
    }

    public void push(int i){
        NodeOfStack newElement = new NodeOfStack(i);
        if(top==null) {
            top = newElement;
            return;
        }

        newElement.preventElement = top;
        top = newElement;
    }

    public Integer pop(){
        if(top==null) return null;
        int value = top.elm;

        top = top.preventElement;
        return value;
    }

    public Integer getTop(){
        if(top==null) return null;
        return top.elm;
    }
}
