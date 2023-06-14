package hashTables;

import model.OaNode;
import java.util.ArrayList;

public class OpenAddressingHT {
    private int sizeOfTable;
    private int numberOfBucketsUsed;
    private double loadFactor;
    ArrayList<OaNode> table;

    public OpenAddressingHT() {
        sizeOfTable = 1;
        table = new ArrayList<>();
        for(int i = 0; i < sizeOfTable; i++) {
            table.add(null);
        }
    }

    private int hash(int key) {
        return key % sizeOfTable;
    }

    public void insert(OaNode nodeToInsert) {
        int position = hash(nodeToInsert.getKey());
        while (table.get(position) != null) {
            position = (position + 1) % sizeOfTable;
        }
        numberOfBucketsUsed++;
        table.set(position, nodeToInsert);
        if (checkLoadFactor() >= 0.7) resizeHashTable(sizeOfTable * 2);
    }

    public OaNode search(OaNode nodeToSearch) {
        int position = hash(nodeToSearch.getKey());
        while (table.get(position).getKey() != nodeToSearch.getKey()) {
            position = (position + 1) % sizeOfTable;
            while (table.get(position) == null) position = (position + 1) % sizeOfTable;
        }
        return table.get(position);
    }

    public void delete(OaNode nodeToDelete) {
        int position = hash(nodeToDelete.getKey());
        while (table.get(position) == null) position = (position + 1) % sizeOfTable;
        while (table.get(position).getKey() != nodeToDelete.getKey()) {
            position = (position + 1) % sizeOfTable;
            while (table.get(position) == null) position = (position + 1) % sizeOfTable;
        }
        table.set(position, null);
        if (table.get(position) == null) numberOfBucketsUsed--;
        loadFactor = checkLoadFactor();
        if (loadFactor <= 0.3) resizeHashTable(sizeOfTable / 2);
    }

    private void resizeHashTable(int sizeOfTable) {
        ArrayList<OaNode> oldTable = table;
        this.sizeOfTable = sizeOfTable;
        numberOfBucketsUsed = 0;
        table = new ArrayList<>(sizeOfTable);
        for (int i = 0; i < sizeOfTable; i++)  table.add(null);
        for (OaNode node : oldTable) {
            if (node != null) insert(node);
        }
    }

    private double checkLoadFactor() {
        return loadFactor = (double) numberOfBucketsUsed / sizeOfTable;
    }
}
