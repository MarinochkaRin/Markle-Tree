package org.mryndina.verkle;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implementation of Verkle Tree, a data structure optimized for compact data representation
 * and integrity verification.
 */
public class VerkleTree {
    private Node rootNode;

    /**
     * Constructs a new Verkle Tree.
     */
    public VerkleTree() {
        rootNode = new Node();
    }

    /**
     * Inserts a key-value pair into the Verkle Tree.
     *
     * @param key   the key associated with the value
     * @param value the value to be inserted
     */
    public void insert(String key, String value) {
        byte[] keyHash = computeHash(key);
        rootNode.insert(keyHash, value);
    }

    /**
     * Retrieves the value associated with a given key from the Verkle Tree.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    public String get(String key) {
        byte[] keyHash = computeHash(key);
        return rootNode.get(keyHash);
    }

    /**
     * Computes the hash of a given data using a cryptographic hash function (e.g., SHA-256).
     *
     * @param data the data to be hashed
     * @return the hash of the data
     */
    byte[] computeHash(String data) {
        // TODO: Implement the actual hash computation using a cryptographic hash function
        return Objects.requireNonNull(data).getBytes(StandardCharsets.UTF_8);
    }

    public void remove(String key) {
        byte[] keyHash = computeHash(key);
        rootNode.remove(keyHash);
    }

    /**
     * Internal node class representing a node in the Verkle Tree.
     */
    private class Node {
        private Map<String, String> leafNodes;
        private Map<String, Node> branchNodes;

        Node() {
            leafNodes = new HashMap<>();
            branchNodes = new HashMap<>();
        }

        /**
         * Inserts a key-value pair into the Verkle Tree starting from this node.
         *
         * @param keyHash the hash of the key associated with the value
         * @param value   the value to be inserted
         */
        void insert(byte[] keyHash, String value) {
            if (keyHash.length == 0) {
                // Insert the value into the leaf node
                leafNodes.put(new String(keyHash), value);
            } else {
                // Take the first byte of the hash as the branch index
                byte index = keyHash[0];

                // Create or retrieve the subtree for the given index
                Node subtree = branchNodes.computeIfAbsent(Byte.toString(index), k -> new Node());

                // Trim the hash, excluding the first byte, and recursively insert into the subtree
                byte[] remainingHash = new byte[keyHash.length - 1];
                System.arraycopy(keyHash, 1, remainingHash, 0, remainingHash.length);
                subtree.insert(remainingHash, value);
            }
        }

        /**
         * Retrieves the value associated with a given key hash from the Verkle Tree starting from this node.
         *
         * @param keyHash the hash of the key to look up
         * @return the value associated with the key, or null if the key is not found
         */
        String get(byte[] keyHash) {
            if (keyHash.length == 0) {
                // Look up the value in the leaf node
                return leafNodes.get(new String(keyHash));
            } else {
                // Take the first byte of the hash as the branch index
                byte index = keyHash[0];

                // Get the subtree for the given index
                Node subtree = branchNodes.get(Byte.toString(index));

                if (subtree != null) {
                    // Trim the hash and recursively search within the subtree
                    byte[] remainingHash = new byte[keyHash.length - 1];
                    System.arraycopy(keyHash, 1, remainingHash, 0, remainingHash.length);
                    return subtree.get(remainingHash);
                } else {
                    return null;
                }
            }
        }
        void remove(byte[] keyHash) {
            if (keyHash.length == 0) {
                // Remove the value from the leaf node
                leafNodes.remove(new String(keyHash));
            } else {
                // Take the first byte of the hash as the branch index
                byte index = keyHash[0];

                // Get the subtree for the given index
                Node subtree = branchNodes.get(Byte.toString(index));

                if (subtree != null) {
                    // Trim the hash and recursively remove from the subtree
                    byte[] remainingHash = new byte[keyHash.length - 1];
                    System.arraycopy(keyHash, 1, remainingHash, 0, remainingHash.length);
                    subtree.remove(remainingHash);

                    // If the subtree becomes empty after removal, remove it from the branch nodes
                    if (subtree.isEmpty()) {
                        branchNodes.remove(Byte.toString(index));
                    }
                }
            }
        }

        boolean isEmpty() {
            return leafNodes.isEmpty() && branchNodes.isEmpty();
        }
    }

    public static void first(String[] args) {
        VerkleTree verkleTree = new VerkleTree();

        // Вставка значений
        verkleTree.insert("key1", "value1");
        verkleTree.insert("key2", "value2");
        verkleTree.insert("key3", "value3");

        // Получение значений
        String value1 = verkleTree.get("key1");
        String value2 = verkleTree.get("key2");
        String value3 = verkleTree.get("key3");

        System.out.println("Value1: " + value1);
        System.out.println("Value2: " + value2);
        System.out.println("Value3: " + value3);
    }
}
