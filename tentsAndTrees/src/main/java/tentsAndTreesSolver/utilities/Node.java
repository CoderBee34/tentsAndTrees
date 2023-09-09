package tentsAndTreesSolver.utilities;

public class Node {
    protected State data;
    protected Node next;

    /**
     * @param data constructs the node by initializing the data with given
     */
    public Node(State data) {
        this.data = data;
        next = null;
    }

    /**
     * @param next sets next to the given node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     *
     * @return returns the next node of given node
     */
    public Node getNext() {
        return next;
    }

    /**
     *
     * @return returns the data of this node
     */
    public State getData() {
        return data;
    }

}

