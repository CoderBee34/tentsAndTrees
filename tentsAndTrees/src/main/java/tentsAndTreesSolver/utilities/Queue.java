package tentsAndTreesSolver.utilities;

import tentsAndTreesSolver.utilities.Node;

public class Queue {

    private Node first;
    private Node last;

    /**
     * constructs the queue by initializing first and last nodes
     */
    public Queue() {
        first = null;
        last = null;
    }

    /**
     * @return returns true if queue is empty otherwise returns false
     */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * @param newNode adds newnode to the end of the queue
     */
    public void enqueue(Node newNode) {
        if (first == null) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    /**
     * @return remove the first node of queue  end returns it
     */
    public Node dequeue(){
        Node result = first;
        if (!isEmpty()){
            first = first.getNext();
            if (first == null){
                last = null;
            }
        }
        return result;
    }
}
