package org.mryndina.merkle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MerkleTree represents a Merkle tree data structure.
 */
public class MerkleTree {

    private List<String> transactions;
    private List<String> merkleTree;

    /**
     * Constructs a MerkleTree object with the given list of transactions.
     *
     * @param transactions the list of transactions
     */
    public MerkleTree(List<String> transactions) {
        this.transactions = removeDuplicateTransactions(transactions);
        this.merkleTree = new ArrayList<>();
    }

    /**
     * Removes duplicate transactions from the given list.
     *
     * @param transactions the list of transactions
     * @return a list of unique transactions
     */
    private List<String> removeDuplicateTransactions(List<String> transactions) {
        Set<String> uniqueSet = new HashSet<>(transactions);
        return new ArrayList<>(uniqueSet);
    }

    /**
     * Builds the Merkle tree from the list of transactions and returns the root hash.
     *
     * @return the root hash of the Merkle tree
     */
    public String build() {
        if (transactions.size() == 1) {
            return hash(transactions.get(0));
        }
        List<String> currentLevel = new ArrayList<>(transactions);

        while (currentLevel.size() > 1) {
            List<String> nextLevel = new ArrayList<>();

            for (int i = 0; i < currentLevel.size(); i += 2) {
                String left = currentLevel.get(i);
                String right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : left;
                String parent = hash(left + right);
                nextLevel.add(parent);
            }

            currentLevel = nextLevel;
        }

        if (currentLevel.size() == 1) {
            return currentLevel.get(0);
        }

        return null; // Return null if there are no transactions
    }

    /**
     * Computes the SHA-256 hash of the given input.
     *
     * @param input the input to hash
     * @return the hash of the input
     */
    String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Main method to demonstrate the usage of MerkleTree class.
     *
     * @param args command-line arguments
     */
    public static void first(String[] args) {
        List<String> transactions = new ArrayList<>();
        transactions.add("Transaction 1");
        transactions.add("Transaction 2");
        transactions.add("Transaction 3");
        transactions.add("Transaction 4");
        transactions.add("Transaction 5");
        transactions.add("Transaction 6");


        MerkleTree merkleTree = new MerkleTree(transactions);
        String root = merkleTree.build();
        System.out.println("Merkle Root: " + root);
    }
}
