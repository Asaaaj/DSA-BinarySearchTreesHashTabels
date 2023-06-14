package trees;

import model.Node;

public class AVLTree {
    public Node root;

    public AVLTree() {
        root = null;
    }

    private Node leftRotate(Node node) {
        Node node2 = node.right;
        if(node2 != null) {
            node.right = node2.left;
            node.setHeight(higherHeight(node));
            node2.left = node;
            node2.setHeight(higherHeight(node2));
        }
        return node2;
    }

    private Node rightRotate(Node node) {
        Node node2 = node.left;
        if (node2 != null) {
            node.left = node2.right;
            node.setHeight(higherHeight(node));
            node2.right = node;
            node2.setHeight(higherHeight(node2));
        }
        return node2;
    }

    public void insert(int key) {
        root = insertIntoTree(root, key);
    }

    private Node insertIntoTree(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            node.setHeight(1);
            return node;
        }
        if (node.getKey() > key) {
            node.left = insertIntoTree(node.left, key);
        }
        else if (node.getKey() < key) {
            node.right = insertIntoTree(node.right, key);
        }

        node.setHeight(higherHeight(node));
        node.setBalanceFactor();
        node = updateBalanceFactor(node);
        return node;
    }

    private Node updateBalanceFactor(Node node, int key) {
        if (node.getBalanceFactor() > 1) {
            if (key < node.left.getKey()) return rightRotate(node);
            else if (key > node.left.getKey()) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (node.getBalanceFactor() < -1) {
            if (key > node.right.getKey()) return leftRotate(node);
            else if (key < node.right.getKey()) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private int higherHeight(Node node) {
        if (node != null) {
            if (returnNumber(node.left) > returnNumber(node.right)) return 1 + node.left.getHeight();
            else if (returnNumber(node.left) < returnNumber(node.right)) return 1 +node.right.getHeight();
            else if (returnNumber(node.left) == returnNumber(node.right)) {
                if(node.left != null) return 1 + node.left.getHeight();
                else return 1;
            }
        }
        return 1;
    }

    public Node search(Node node, int key) {
        if (node == null) return null;
        else if (node.getKey() == key) return node;
        else if (node.getKey() > key) return search(node.left, key);
        else if (node.getKey() < key) return search(node.right, key);
        return null;
    }

    public void delete(int key) {
        root = deleteFromTree(root, key);
    }

    private Node deleteFromTree(Node node, int key) {
        if (node == null) return null;
        else if (node.getKey() > key) node.left = deleteFromTree(node.left, key);
        else if (node.getKey() < key) node.right = deleteFromTree(node.right, key);
        else {
            //CASE 1
            if (node.left == null && node.right == null) return null;
            //CASE 2
            else if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            //CASE 3
            else {
                node.setKey(findInorderSuccessor(node));
                node.right = deleteFromTree(node.right, node.getKey());
                if (node.right != null) {
                    node.right.setHeight(higherHeight(node.right));
                    node.right.setBalanceFactor();
                    node.right = updateBalanceFactor(node.right);
                }
            }
        }
        node.setHeight(higherHeight(node));
        node.setBalanceFactor();
        node = updateBalanceFactor(node);
        return node;
    }

    private int findInorderSuccessor(Node node) {
        node = node.right;
        while(node.left != null) node = node.left;
        //System.out.println("Inorder successor: " + node.getKey());
        return node.getKey();
    }

    private Node updateBalanceFactor(Node node) {
        if (node.getBalanceFactor() > 1) {
            if (node.left.getBalanceFactor() >= 0) return rightRotate(node);
            else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        else if (node.getBalanceFactor() < -1) {
            if (node.right.getBalanceFactor() <= 0) return leftRotate(node);
            else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private int returnNumber(Node node) {
        if (node == null) return 0;
        else return node.getHeight();
    }
}