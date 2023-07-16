package org.mryndina.sparkle;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {




    public static void main(String[] args) {
        System.out.println("Sparse Patricia Tree ");
        SparseMerkleTree.first(args);
        System.out.println("Test1");
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
    }

    public static void test1() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 2");
        data.add("Transaction 3");
        data.add("Transaction 4");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String rootHash = merkleTree.getRootHash();
        System.out.println("Root Hash: " + rootHash);
    }

    public static void test2() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String rootHash = merkleTree.getRootHash();
        System.out.println("Root Hash: " + rootHash);
    }

    public static void test3() {
        List<String> data = new ArrayList<>();

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String rootHash = merkleTree.getRootHash();

        if (rootHash == null) {
            System.out.println("Merkle Root: No transactions available.");
        } else {
            System.out.println("Merkle Root: " + rootHash);
        }
    }

    public static void test4() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 3");
        data.add("Transaction 1");
        data.add("Transaction 4");
        data.add("Transaction 2");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String rootBefore = merkleTree.getRootHash();

        Collections.shuffle(data);

        String rootAfter = merkleTree.getRootHash();
        System.out.println("Root Before Shuffling: " + rootBefore);
        System.out.println("Root After Shuffling: " + rootAfter);
    }

    public static void test5() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 2");
        data.add("Transaction 3");
        data.add("Transaction 4");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);

        // Add new transactions
        data.add("Transaction 5");
        data.add("Transaction 6");

        SparseMerkleTree updatedMerkleTree = new SparseMerkleTree(data);
        String updatedRoot = updatedMerkleTree.getRootHash();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

    public static void test6() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 2");
        data.add("Transaction 3");
        data.add("Transaction 4");
        data.add("Transaction 5");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);

        // Remove a transaction
        data.remove(2);

        SparseMerkleTree updatedMerkleTree = new SparseMerkleTree(data);
        String updatedRoot = updatedMerkleTree.getRootHash();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

    public static void test7() {
        List<String> data = new ArrayList<>();

        // Generate a large number of transactions
        for (int i = 1; i <= 10000; i++) {
            data.add("Transaction " + i);
        }

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);
    }

    public static void test8() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 2");
        data.add("Transaction 3");
        data.add("Transaction 4");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);

        data.add("Transaction 1");

        SparseMerkleTree updatedMerkleTree = new SparseMerkleTree(data);
        String updatedRoot = updatedMerkleTree.getRootHash();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

    public static void test9() {
        List<String> data = new ArrayList<>();

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();

        if (root == null) {
            System.out.println("Test passed: Empty Merkle tree");
        } else {
            System.out.println("Test failed: Empty Merkle tree");
        }
    }

    public static void test10() {
        List<String> data = new ArrayList<>();

        // Generate a large number of transactions
        for (int i = 1; i <= 1000; i++) {
            data.add("Transaction " + i);
        }

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);
    }

    public static void test11() {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 1");
        data.add("Transaction 1");
        data.add("Transaction 1");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String root = merkleTree.getRootHash();
        System.out.println("Merkle Root: " + root);
    }
}