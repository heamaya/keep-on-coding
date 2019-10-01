package chapter21.exercise19;

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

    public long depth() {
        return depth(getRoot());
    }

    private long depth(TreeNode<E> node) {
        if (node != null) {
            long leftDepth = depth(node.getLeftNode());
            long rightDepth = depth(node.getRightNode());
            if (leftDepth > rightDepth) {
                return leftDepth + 1;
            }
            else {
                return rightDepth + 1;
            }
        } else {
            return 0;
        }
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public static void main(String [] args) {
        final SecureRandom secureRandom = new SecureRandom();
        final Tree<Integer> tree = new Tree<>();
        secureRandom.ints(20, 1, 100)
            .forEach(i -> tree.insert(i));
        System.out.println("In Order Traversal:");
        tree.inOrderTraversal();
        System.out.println();
        System.out.printf("The depth of the tree is: %d%n", tree.depth());
    }

}
