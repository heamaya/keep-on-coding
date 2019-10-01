package chapter21.exercise16;

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

    public TreeNode<E> getRoot() {
        return root;
    }

    public static void main(String [] args) {
        final SecureRandom secureRandom = new SecureRandom();
        final Tree<Integer> tree = new Tree<>();
        secureRandom.ints(16, 1, 5)
            .forEach(i -> tree.insert(i));
        System.out.println("Pre Order Traversal:");
        tree.preOrderTraversal();
        System.out.println();
        System.out.println("In Order Traversal:");
        tree.inOrderTraversal();
        System.out.println();
        System.out.println("Pos Order Traversal:");
        tree.posOrderTraversal();
        System.out.println();
    }

}
