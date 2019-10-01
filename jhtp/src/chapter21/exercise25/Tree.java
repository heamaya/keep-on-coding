package chapter21.exercise25;

import chapter21.datastructures.TreeNode;

import java.security.SecureRandom;

public class Tree<E extends Comparable<E>> {

    private TreeNode<E> root;

    public void insert(E element) {
        if (root == null) {
            root = new TreeNode<>(element);
        } else {
            root.insert(element);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            System.out.printf("%s ", node.getElement());
            preOrderTraversal(node.getLeftNode());
            preOrderTraversal(node.getRightNode());
        }
    }


    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            inOrderTraversal(node.getLeftNode());
            System.out.printf("%s ", node.getElement());
            inOrderTraversal(node.getRightNode());
        }
    }

    public void posOrderTraversal() {
        posOrderTraversal(root);
    }

    private void posOrderTraversal(TreeNode<E> node) {
        if (node != null) {
            posOrderTraversal(node.getLeftNode());
            System.out.printf("%s ", node.getElement());
            posOrderTraversal(node.getRightNode());
        }
    }

    public void ouputTree() {
        outputTree(getRoot(), 0);
    }

    private void outputTree(TreeNode<E> node, int totalSpaces) {
        while (node != null) {
            outputTree(node.getRightNode(), totalSpaces + 5);
            for (int i = 1; i < totalSpaces; i++) {
                System.out.print(" ");
            }
            System.out.printf("%s%n", node.getElement());
            node = node.getLeftNode();
            totalSpaces += 5;
        }
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public static void main(String [] args) {
        final SecureRandom secureRandom = new SecureRandom();
        final Tree<Integer> tree = new Tree<>();
        secureRandom.ints(16, 1, 100)
            .forEach(i -> tree.insert(i));
        System.out.println("Output Tree:");
        tree.ouputTree();
    }



}
