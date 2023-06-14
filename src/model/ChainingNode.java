package model;

public class ChainingNode {
    private int key;
    private int data;
    private ChainingNode next;

    public ChainingNode(ChainingNode node) {
        key = node.key;
        data = node.data;
        next = node.next;
    }

    public ChainingNode() {

    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ChainingNode getNext() {
        return next;
    }

    public ChainingNode setNext(ChainingNode next) {
        if (next == null) this.next = null;
        else this.next = new ChainingNode(next);
        return next;
    }
    public void nodeNext(ChainingNode next) {
        if (next == null) this.next = null;
        else this.next = next;
    }
}