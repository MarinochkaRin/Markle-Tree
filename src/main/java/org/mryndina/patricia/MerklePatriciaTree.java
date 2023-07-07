package org.mryndina.patricia;

import java.util.HashMap;
import java.util.Map;

/**
 * Merkle Patricia Tree implementation for key-value storage and retrieval.
 */
public class MerklePatriciaTree {

    private Node rootNode; // Root node of the Merkle Patricia Tree

    /**
     * Constructs a new Merkle Patricia Tree.
     */
    public MerklePatriciaTree() {
        rootNode = new Node();
    }

    /**
     * Inserts a key-value pair into the Merkle Patricia Tree.
     *
     * @param key   the key to insert
     * @param value the corresponding value
     */
    public void insert(String key, String value) {
        rootNode.insert(key, value);
    }

    /**
     * Retrieves the value associated with the given key from the Merkle Patricia Tree.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if not found
     */
    public String get(String key) {
        return rootNode.get(key);
    }

    /**
     * Represents a node in the Merkle Patricia Tree.
     */
    private static class Node {
        private String value; // The value stored in the node (null for non-leaf nodes)
        private Map<Character, Node> children; // Children nodes

        /**
         * Constructs a new node.
         */
        public Node() {
            this.value = null;
            this.children = new HashMap<>();
        }

        /**
         * Inserts a key-value pair into the node and its children.
         *
         * @param key   the key to insert
         * @param value the corresponding value
         */
        public void insert(String key, String value) {
            if (key.isEmpty()) {
                this.value = value; // Reached the end of the key, store the value in this node
                return;
            }

            char firstChar = key.charAt(0);
            Node child = children.get(firstChar);

            if (child == null) {
                child = new Node(); // Create a new child node if it doesn't exist
                children.put(firstChar, child);
            }

            child.insert(key.substring(1), value); // Recursively insert the remaining part of the key into the child node
        }

        /**
         * Retrieves the value associated with the given key from the node and its children.
         *
         * @param key the key to search for
         * @return the value associated with the key, or null if not found
         */
        public String get(String key) {
            if (key.isEmpty()) {
                return value; // Reached the end of the key, return the value stored in this node
            }

            char firstChar = key.charAt(0);
            Node child = children.get(firstChar);

            if (child == null) {
                return null; // Key not found in the tree
            }

            return child.get(key.substring(1)); // Recursively search for the remaining part of the key in the child node
        }
    }

    /**
     * Demonstrates the usage of the Merkle Patricia Tree.
     *
     * @param args command line arguments (not used)
     */
    public static void first(String[] args) {
        MerklePatriciaTree tree = new MerklePatriciaTree();

        tree.insert("apple", "red");
        tree.insert("banana", "yellow");
        tree.insert("orange", "orange");

        System.out.println(tree.get("apple"));
        System.out.println(tree.get("banana"));
        System.out.println(tree.get("orange"));
        System.out.println(tree.get("grape"));   // Output: null
    }
}
