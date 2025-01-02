package com.example.assignment;

public class BinaryTree {
    private Node root;

    public void insert(Finance finance) {
        root = insertRec(root, finance);
    }

    private Node insertRec(Node root, Finance finance) {
        if (root == null) {
            root = new Node(finance);
            return root;
        }

        if (finance.getMonth().compareTo(root.finance.getMonth()) < 0) {
            root.left = insertRec(root.left, finance);
        } else if (finance.getMonth().compareTo(root.finance.getMonth()) > 0) {
            root.right = insertRec(root.right, finance);
        }
        return root;
    }

    public Finance find(String month) {
        return findRec(root, month);
    }

    private Finance findRec(Node root, String month) {
        if (root == null || root.finance.getMonth().equals(month)) {
            return root != null ? root.finance : null;
        }

        if (month.compareTo(root.finance.getMonth()) < 0) {
            return findRec(root.left, month);
        }
        return findRec(root.right, month);
    }
}