package hashTables;

import java.util.ArrayList;

import model.ChainingNode;

public class ChainingHT {
    private int sizeOfTable;
    private int numberOfBucketsUsed;
    private double loadFactor;
    ArrayList<ChainingNode> table;

    public ChainingHT() {
        sizeOfTable = 1;
        table = new ArrayList<>();
        for(int i = 0; i < sizeOfTable; i++) {
            table.add(null);
        }
    }

    private int hash(int key) {
        return key % sizeOfTable;
    }

    public void insert(ChainingNode node) {
        int position = hash(node.getKey());
        if(table.get(position) == null) numberOfBucketsUsed++;
        table.set(position, insertIntoBucket(table.get(position), node));

        if (checkLoadFactor() >= 0.8) resizeHashTable(sizeOfTable * 2);
    }

    private ChainingNode insertIntoBucket(ChainingNode node, ChainingNode dataNode) {
        if (node == null) {
            node = new ChainingNode(dataNode);
            return node;
        }
        else {
            ChainingNode head = node;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(insertIntoBucket(node.getNext(), dataNode));
            return head;
        }
    }

    public ChainingNode search(ChainingNode searchingNode) {
        int position = hash(searchingNode.getKey());
        ChainingNode node = table.get(position);
        while (node.getKey() != searchingNode.getKey()) {
            node = node.getNext();
        }
        return node;
    }

    public void delete(ChainingNode deletingNode) {
        int position = hash(deletingNode.getKey());
        ChainingNode node = table.get(position);
        ChainingNode head = node;
            if(node.getNext() == null) table.set(position, null);
            else {
                if(node.getKey() == deletingNode.getKey()) table.set(position, node.getNext());
                else {
                    while (node.getNext() != null) {
                        if (node.getNext().getKey() == deletingNode.getKey()) node.nodeNext(node.getNext().getNext());
                        else node = node.getNext();
                    }
                    table.set(position,head);
                }
            }
            if (table.get(position) == null) numberOfBucketsUsed--;
            loadFactor = checkLoadFactor();
            if (loadFactor <= 0.3) resizeHashTable(sizeOfTable / 2);
    }

    private double checkLoadFactor(){
        return loadFactor = (double) numberOfBucketsUsed / sizeOfTable;
    }

    private void resizeHashTable(int sizeOfTable) {
        ArrayList<ChainingNode> oldTable = table;
        this.sizeOfTable = sizeOfTable;
        numberOfBucketsUsed = 0;
        table = new ArrayList<>(sizeOfTable);
        for(int i = 0; i < sizeOfTable; i++) {
            table.add(null);
        }
        for(ChainingNode bucket : oldTable) {
            while (bucket != null) {
                ChainingNode node = bucket.getNext();
                bucket.setNext(null);
                insert(bucket);
                bucket = node;
            }
        }
    }
}


