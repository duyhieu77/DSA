package com.example.assignment;

public class Node {
    Finance finance;
    Node left, right;

    public Node(Finance finance) {
        this.finance = finance;
        this.left = null;
        this.right = null;
    }
}