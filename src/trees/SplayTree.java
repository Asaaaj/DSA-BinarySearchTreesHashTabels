package trees;

import model.Node;

public class SplayTree {
    public Node root;
    private Node nodeToSplay;

    public SplayTree() {
        root = null;
    }

    private Node leftRotate(Node node) {
        Node node2 = node.right;
        node.right = node2.left;
        if (node.right != null) node.right.setParent(node);
        if(node.getParent() != null && node == node.getParent().left) node.getParent().left = node2;
        else if(node.getParent() != null && node == node.getParent().right) node.getParent().right = node2;
        node2.left = node;
        node2.setParent(node.getParent());
        node.setParent(node2);
        return node2;
    }

    private Node rightRotate(Node node) {
        Node node2 = node.left;
        node.left = node2.right;
        if (node.left != null) node.left.setParent(node);
        if(node.getParent() != null && node == node.getParent().left) node.getParent().left = node2;
        else if(node.getParent() != null && node == node.getParent().right) node.getParent().right = node2;
        node2.right = node;
        node2.setParent(node.getParent());
        node.setParent(node2);
        return node2;
    }

    private Node splay(Node node) {
        nodeToSplay = null;
        while (node.getParent() != null) {
            if (node.getParent() == root) {
                if (node == node.getParent().left) {
                    return root = rightRotate(node.getParent());
                }
                else if(node == node.getParent().right){
                    return root = leftRotate(node.getParent());
                }
            }
            else {
                Node parent = node.getParent();
                Node grandParent = parent.getParent();
                if (node == node.getParent().left && parent == parent.getParent().left) {
                    grandParent = rightRotate(grandParent);
                    parent = rightRotate(parent);
                }
                else if (node == node.getParent().right && parent == parent.getParent().right) {
                    grandParent = leftRotate(grandParent);
                    parent = leftRotate(parent);
                }
                else if (node == node.getParent().left && parent == parent.getParent().right) {
                    parent = rightRotate(parent);
                    grandParent = leftRotate(grandParent);
                }
                else {
                    parent = leftRotate(parent);
                    grandParent = rightRotate(grandParent);
                }
            }
        }
        return node;
    }

    public Node search(Node node, int key) {
        while (node != null) {
                if(node.getKey() == key) {
                    return root = splay(node);
                }
                if(node.getKey() > key) {
                    node = node.left;

                }
                else if(node.getKey() < key){
                    node = node.right;
                }
            }
        return null;
    }

    public void insert(int key) {
        root = insertIntoTree(root, key);
        root = splay(nodeToSplay);
    }

    private Node insertIntoTree(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            nodeToSplay = node;
            return node;
        }
        if (node.getKey() > key) {
            node.left = insertIntoTree(node.left, key);
            node.left.setParent(node);
        }
        else if (node.getKey() < key) {
            node.right = insertIntoTree(node.right, key);
            node.right.setParent(node);
        }
        return node;
    }

    public void delete(int key) {
        root = search(root, key);
        if (root != null) {
            if (root.left == null) {
                root = root.right;
                if(root != null) root.setParent(null);
            }
            else {
                Node nodeRight = root.right;
                Node node = findPreorderSuccessor(root);
                if (node != null) root = splay(node);
                root.right = nodeRight;
                if (nodeRight != null) nodeRight.setParent(root);
            }
        }
    }

    private Node findPreorderSuccessor(Node node) {
        node = node.left;
        while (node != null && node.right != null) node = node.right;
        return node;
    }
}