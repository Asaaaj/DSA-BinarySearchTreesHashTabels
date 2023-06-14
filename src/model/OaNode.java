package model;

public class OaNode {
    int key;
    String data;

    public OaNode(OaNode node) {
        key = node.key;
        data = node.data;
    }

    public OaNode() {

    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


