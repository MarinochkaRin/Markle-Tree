package org.mryndina.merkle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args){
        System.out.println("Merkle Tree ");
        MerkleTree.first(args);
        System.out.println("Test1");
        test1(args);
        System.out.println("Test2");
        test2(args);
        System.out.println("Test3");
        test3(args);
        System.out.println("Test4");
        test4(args);
        System.out.println("Test5");
        test5(args);
        System.out.println("Test6");
        test6(args);
        System.out.println("Test7");
        test7(args);
        System.out.println("Test8");
        test8(args);
    }
    public static void test1(String[] args) {

        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");
        transactions.add("Transaction 2");
        transactions.add("Transaction 3");
        transactions.add("Transaction 4");


        MerkleTree merkleTree = new MerkleTree(transactions);


        String expectedRootHash = "0bc1c5cf4cc8f4915cdf888eca02682416c6be663d7706b9fb0933038ab9981a";

        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);
        if (root.equals(expectedRootHash)) {
            System.out.println("Ok");
        } else {
            System.out.println("No");
        }
    }

    public static void test2(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");

        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);

        if (root != null && root.equals(merkleTree.hash("Transaction 1"))) {
            System.out.println("Test passed: Merkle tree with single transaction");
        } else {
            System.out.println("Test failed: Merkle tree with single transaction");
        }
    }
    public static void test3(String[] args) {
        List<String> transactions = new ArrayList<>();

        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();

        if (root == null) {
            System.out.println("Merkle Root: No transactions available.");
        } else {
            System.out.println("Merkle Root: " + root);
        }
    }

    public static void test4(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 3");
        transactions.add("Transaction 1");
        transactions.add("Transaction 4");
        transactions.add("Transaction 2");

        MerkleTree merkleTree = new MerkleTree(transactions);
        String rootBefore = merkleTree.build();

        Collections.shuffle(transactions);

        System.out.println("Root: " + rootBefore);
    }
    public static void test5(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");
        transactions.add("Transaction 2");
        transactions.add("Transaction 3");
        transactions.add("Transaction 4");

        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);

        // Add a new transaction
        transactions.add("Transaction 5");
        transactions.add("Transaction 6");

        // Reconstruct the Merkle tree with the updated transactions
        merkleTree = new MerkleTree(transactions);
        String updatedRoot = merkleTree.build();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

    public static void test6(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");
        transactions.add("Transaction 2");
        transactions.add("Transaction 3");
        transactions.add("Transaction 4");
        transactions.add("Transaction 5");

        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);

        // Remove a transaction
        transactions.remove(2);

        MerkleTree updatedMerkleTree = new MerkleTree(transactions);
        String updatedRoot = updatedMerkleTree.build();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

        public static void test7(String[] args) {
            List<String> transactions = new ArrayList<>();

            // Generate a large number of transactions
            for (int i = 1; i <= 10000; i++) {
                transactions.add("Transaction " + i);
            }

            MerkleTree merkleTree = new MerkleTree(transactions);
            String root = merkleTree.build();
            System.out.println("Merkle Root: " + root);
        }

    public static void test8(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");
        transactions.add("Transaction 2");
        transactions.add("Transaction 3");
        transactions.add("Transaction 4");

        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);

        transactions.add("Transaction 1");

        merkleTree = new MerkleTree(transactions);
        String updatedRoot = merkleTree.build();
        System.out.println("Updated Merkle Root: " + updatedRoot);
    }

}
