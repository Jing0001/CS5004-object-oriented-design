package bignumber;

public class Node {
    //member variable
    public Node prev;
    public int val;
    public Node next;
    //constructor
    public Node(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
    //constructor
    public Node(Node prev, int val, Node next) {
        this.prev = prev;
        this.val = val;
        this.next = next;
    }
}
