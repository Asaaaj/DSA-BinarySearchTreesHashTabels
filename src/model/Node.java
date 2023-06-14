package model;

public class Node {
    private int key;
    private int height;
    private int balanceFactor;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int key) {
        this.key = key;
        height = 1;
    }

    public int getKey() {
        return key;
}

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor() {
        balanceFactor = returnNumber(left) - returnNumber(right);
    }

    private int returnNumber(Node node) {
        if (node == null) return 0;
        else return node.getHeight();
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
