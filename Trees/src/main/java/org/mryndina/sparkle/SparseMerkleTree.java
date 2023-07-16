package org.mryndina.sparkle;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;





public class SparseMerkleTree {

    private MerkleNode root;
    public SparseMerkleTree(List<String> data) {
        this.root = buildTree(data);
    }

    private MerkleNode buildTree(List<String> data) {
        List<MerkleNode> leaves = new ArrayList<>();

        // Convert data into MerkleNode leaves
        for (String datum : data) {
            leaves.add(new MerkleNode(applyHash(datum)));
        }

        while (leaves.size() > 1) {
            List<MerkleNode> parents = new ArrayList<>();

            // Combine leaves in pairs to create parent nodes
            for (int i = 0; i < leaves.size(); i += 2) {
                MerkleNode leftChild = leaves.get(i);
                MerkleNode rightChild = (i + 1 < leaves.size()) ? leaves.get(i + 1) : null;
                String combinedHash = (rightChild != null) ? applyHash(leftChild.hash + rightChild.hash) : leftChild.hash;
                MerkleNode parent = new MerkleNode(combinedHash);
                parent.left = leftChild;
                parent.right = rightChild;
                parent.height = Math.max(leftChild.height, rightChild.height) + 1;
                parents.add(parent);
            }

            leaves = parents;
        }

        return leaves.get(0); // Return the root of the Merkle tree
    }

    private String applyHash(String input) {
        return DigestUtils.sha256Hex(input);
    }

    public String getRootHash() {
        return (root != null) ? root.hash : null;
    }

    public static void first(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Transaction 1");
        data.add("Transaction 2");
        data.add("Transaction 3");
        data.add("Transaction 4");

        SparseMerkleTree merkleTree = new SparseMerkleTree(data);
        String rootHash = merkleTree.getRootHash();
        System.out.println("Root Hash: " + rootHash);
    }
}