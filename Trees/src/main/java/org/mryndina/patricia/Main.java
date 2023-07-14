package org.mryndina.patricia;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class Main {
    public static void main(String[] args) {
        System.out.println("Merkle Patricia Tree ");
        MerklePatriciaTree.first(args);
        System.out.println("Test1");
        test1();
        System.out.println("Test2");
        test2();
        System.out.println("Test3");
        test3();
        System.out.println("Test4");
        test4();
    }



        public static void  test1() {
            MerklePatriciaTree tree = new MerklePatriciaTree();

            // Insert key-value pair "apple" -> "red"
            tree.insert("apple", "red");

            // Retrieve the value for the key "apple" and verify it is "red"
            String retrievedValue = tree.get("apple");
            System.out.println("Retrieved value for key 'apple': " + retrievedValue); // Expected output: red

            // Insert the same key with a different value "apple" -> "green"
            tree.insert("apple", "green");

            // Retrieve the value for the key "apple" again and verify it is now "green"
            retrievedValue = tree.get("apple");
            System.out.println("Retrieved value for key 'apple' after overwriting: " + retrievedValue); // Expected output: green

        }

    public static void test2() {
        // Create an empty Merkle Patricia Tree
        MerklePatriciaTree tree = new MerklePatriciaTree();

        // Attempt to retrieve a value for a key
        String value = tree.get("key");

        // Print a message indicating whether the tree is empty
        if (value == null) {
            System.out.println("The tree is empty.");
        } else {
            System.out.println("The tree is not empty.");
        }
    }

    public static void test3() {
        MerklePatriciaTree tree = new MerklePatriciaTree();

        // Generate a large number of key-value pairs
        int numPairs = 10000;
        for (int i = 0; i < numPairs; i++) {
            String key = "key" + i;
            String value = "value" + i;
            tree.insert(key, value);
            System.out.println("Inserted key: " + key + ", value: " + value);
        }

        // Retrieve values for the keys and verify their correctness
        for (int i = 0; i < numPairs; i++) {
            String key = "key" + i;
            String expectedValue = "value" + i;
            String retrievedValue = tree.get(key);
            Assertions.assertEquals(expectedValue, retrievedValue);
            System.out.println("Retrieved key: " + key + ", value: " + retrievedValue);
        }

        // Test passed if no assertions failed
        System.out.println("Large key set test passed successfully.");
    }

    public static void test4() {
        MerklePatriciaTree tree = new MerklePatriciaTree();

        // Generate a large number of key-value pairs
        int numPairs = 100000;
        Map<String, String> keyValuePairs = generateKeyValuePairs(numPairs);

        // Measure the time taken to insert the key-value pairs
        long startTime = System.nanoTime();
        for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
            tree.insert(entry.getKey(), entry.getValue());
        }
        long endTime = System.nanoTime();
        long insertionTime = endTime - startTime;

        // Measure the time taken to retrieve values for different keys
        startTime = System.nanoTime();
        for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
            String retrievedValue = tree.get(entry.getKey());
            assertEquals(entry.getValue(), retrievedValue);
        }
        endTime = System.nanoTime();
        long retrievalTime = endTime - startTime;

        // Output the performance results
        System.out.println("Number of key-value pairs: " + numPairs);
        System.out.println("Insertion time: " + insertionTime + " ns");
        System.out.println("Retrieval time: " + retrievalTime + " ns");

    }
    private static Map<String, String> generateKeyValuePairs(int numPairs) {
        Map<String, String> keyValuePairs = new HashMap<>();
        for (int i = 0; i < numPairs; i++) {
            String key = "key_" + i;
            String value = "value_" + i;
            keyValuePairs.put(key, value);
        }
        return keyValuePairs;
    }
}