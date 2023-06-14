import hashTables.ChainingHT;
import hashTables.OpenAddressingHT;
import model.ChainingNode;
import model.Node;
import model.OaNode;
import trees.AVLTree;
import trees.SplayTree;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("DSA - Zadanie 1");

//        ArrayList<Integer> openRandomNumberss = new ArrayList<>();
//        for(int i = 0; i < 10000000; i++) openRandomNumberss.add(i);
//        Collections.shuffle(openRandomNumberss);
//
//
//        SplayTree s = new SplayTree();
//        Instant start1 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            s.insert(openRandomNumberss.get(i));
//        }
//        Instant end1 = Instant.now();
//        System.out.println("Duration splay insert: " + Duration.between(start1, end1).toMillis() + " ms");
//
//        Instant start2 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            s.search(s.root, openRandomNumberss.get(i));
//        }
//        Instant end2 = Instant.now();
//        System.out.println("Duration splay search: " + Duration.between(start2, end2).toMillis() + " ms");
//
//        Instant start3 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            s.delete(openRandomNumberss.get(i));
//        }
//        Instant end3 = Instant.now();
//        System.out.println("Duration splay delete: " + Duration.between(start3, end3).toMillis() + " ms");
//
//
//
//        AVLTree avl = new AVLTree();
//        Instant startAVL1 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            avl.insert(openRandomNumberss.get(i));
//        }
//        Instant endAVL1 = Instant.now();
//        System.out.println("Duration AVL insert: " + Duration.between(startAVL1, endAVL1).toMillis() + " ms");
//
//        Instant startAVL2 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            avl.search(avl.root, openRandomNumberss.get(i));
//        }
//        Instant endAVL2 = Instant.now();
//        System.out.println("Duration AVL search: " + Duration.between(startAVL2, endAVL2).toMillis() + " ms");
//
//        Instant startAVL3 = Instant.now();
//        for(int i = 0; i < 10000000; i++) {
//            avl.delete(openRandomNumberss.get(i));
//        }
//        Instant endAVL3 = Instant.now();
//        System.out.println("Duration AVL delete: " + Duration.between(startAVL3, endAVL3).toMillis() + " ms");
//
//
//        ChainingHT chain = new ChainingHT();
//        ChainingNode[] chainingNodes = new ChainingNode[10000000];
//        ArrayList<Integer> randomNumbers = new ArrayList<>();
//        for(int i = 0; i < 10000000; i++) randomNumbers.add(i);
//        Collections.shuffle(randomNumbers);
//        for (int i = 0; i < chainingNodes.length; i++) {
//            chainingNodes[i] = new ChainingNode();
//            chainingNodes[i].setKey(openRandomNumberss.get(i));
//            chainingNodes[i].setData(5);
//        }
//
//        Instant startChain1 = Instant.now();
//        for (ChainingNode value : chainingNodes) {
//            chain.insert(value);
//        }
//        Instant endChain1 = Instant.now();
//        System.out.println("Duration Chaining insert: " + Duration.between(startChain1, endChain1).toMillis() + " ms");
//
//        Instant startChain2 = Instant.now();
//        for (ChainingNode node : chainingNodes) {
//            chain.search(node);
//        }
//        Instant endChain2 = Instant.now();
//        System.out.println("Duration Chaining search: " + Duration.between(startChain2, endChain2).toMillis() + " ms");
//
//        Instant startChain3 = Instant.now();
//        for (ChainingNode chainingNode : chainingNodes) {
//            chain.delete(chainingNode);
//        }
//        Instant endChain3 = Instant.now();
//        System.out.println("Duration Chaining delete: " + Duration.between(startChain3, endChain3).toMillis() + " ms");


        OpenAddressingHT open = new OpenAddressingHT();
        OaNode[] openNodes = new OaNode[4220000];
        ArrayList<Integer> openRandomNumbers = new ArrayList<>();
        for(int i = 0; i < 4220000; i++) openRandomNumbers.add(i);
        Collections.shuffle(openRandomNumbers);
        for (int i = 0; i < openNodes.length; i++) {
            openNodes[i] = new OaNode();
            openNodes[i].setKey(openRandomNumbers.get(i));
            openNodes[i].setData("Asaj");
        }
        System.out.println("start");
        Instant startOpen1 = Instant.now();
        for (OaNode value : openNodes) {
            open.insert(value);
        }
        Instant endOpen1 = Instant.now();
        System.out.println("Duration Open insert: " + Duration.between(startOpen1, endOpen1).toMillis() + " ms");

        Instant startOpen2 = Instant.now();
        for (OaNode value : openNodes) {
            open.search(value);
        }
        Instant endOpen2 = Instant.now();
        System.out.println("Duration Open search: " + Duration.between(startOpen2, endOpen2).toMillis() + " ms");

        Instant startOpen3 = Instant.now();
        for (OaNode value : openNodes) {
            open.delete(value);
        }
        Instant endOpen3 = Instant.now();
        System.out.println("Duration Open delete: " + Duration.between(startOpen3, endOpen3).toMillis() + " ms");
    }
}