package utils;

public class Queue {
    public Node queue = null;
    public Node lastQueue = null;

    public boolean enqueue(int data){
        if(data<0) return false;

        Node elmNew = new Node(data);

        if(this.queue == null) {
            this.queue = elmNew;
            this.lastQueue = elmNew;
            return true;
        }

        this.lastQueue.nextNode = elmNew;
        this.lastQueue = elmNew;

        return true;
    }

    public Node denqueue(){
        if(lastQueue == null ) return null;

        Node lastNode = queue;

        queue = queue.nextNode;

        return lastNode;
    }

    public boolean enqueueFront(int data){
        if(data<0) return false;

        Node elmNew = new Node(data);

        if(this.queue == null) {
            this.queue = elmNew;
            this.lastQueue = elmNew;
            return true;
        }

        elmNew.nextNode = this.queue;
        this.queue = elmNew;

        return true;
    }

    public void printQueue(){
        Node lastNode = this.lastQueue;

        Node aux = this.queue;
        Node auxPrev = null;

        while ( aux != null ){
            System.out.print(aux.data+" -> ");
            auxPrev = aux;
            aux = aux.nextNode;
        }
        System.out.println("null");
    }
}
