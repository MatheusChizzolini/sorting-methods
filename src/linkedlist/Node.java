package linkedlist;

public class Node {
    private int info;
    private Node prev, next;

    public Node(int info, Node prev, Node next) {
        this.info = info;
        this.prev = prev;
        this.next = next;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
