package org.mryndina.sparkle;

class MerkleNode {
    String hash;
    MerkleNode left;
    MerkleNode right;
    int height;
    public MerkleNode(String hash) {
        this.hash = hash;
        this.height = 0;
    }
}
