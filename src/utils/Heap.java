package utils;

public class Heap {
    public Node[] heap;
    private int[] local;
    public int size;

    public Heap(int heapLength) {
        heap = new Node[heapLength + 1];
        local = new int[heapLength];
        size = 0;
    }

    private int parent(int i) { return i / 2; }
    private int left(int i) { return 2 * i; }
    private int right(int i) { return 2 * i + 1; }

    public void insert(Node node) {
        if (size >= heap.length - 1) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heap[size+1] = node;
        local[node.data] = size+1;
        ++size;
        for(int i = size;i>=1;i=i/2)
            siftDown(i);
    }

    public Node removeMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        Node min = heap[1];
        heap[1] = heap[size--];
        siftDown(1);
        return min;
    }

    private void siftDown(int i) {
        int minIndex = i;
        int left = left(i);
        int right = right(i);

        if (left <= size && heap[left].d < heap[minIndex].d) {
            minIndex = left;
        }
        if (right <= size && heap[right].d < heap[minIndex].d) {
            minIndex = right;
        }
        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }

    }

    private void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void printHeap(){
        System.out.println("Heap:");
        for(int n = 1; n<=size;n++){
            System.out.print(heap[n].data + " ~> ");
        }
        System.out.println("null\n");
    }

    public void decreaseKey(int local, Float newValue) {
        for(int i=0;i<size;i++){
            if(heap[i].data == local)
                heap[i].d = newValue;
        }

        for(int g = size;g>=1;g=g/2)
            siftDown(g);
    }

    public void decreaseKey(Node local, Float newValue) {
        local.d = newValue;

        for(int g = size;g>=1;g=g/2)
            siftDown(g);
    }

    public Node extractMin() {
        if (size == 0) {
            return null;
        }

        Node min = heap[1];
        heap[1] = heap[size--];
        siftDown(1);
        return min;
    }
}

