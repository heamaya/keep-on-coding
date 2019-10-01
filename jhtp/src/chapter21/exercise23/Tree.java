package chapter21.exercise23;

import chapter21.datastructures.Queue;
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

    public void output() {
        output(getRoot(), 0);
    }

    private void output(TreeNode<E> node, int totalSpaces) {
        while (node != null) {
            output(node.getRightNode(), totalSpaces + 5);
            for (int i = 1; i < totalSpaces; i++) {
                System.out.print(" ");
            }
            System.out.printf("%s%n", node.getElement());
            node = node.getLeftNode();
            totalSpaces += 5;
        }
    }

    public void levelTraversal() {
        final Queue<TreeNode<E>> queue = new Queue<>();
        queue.enqueue(getRoot());
        while (!queue.isEmpty()) {
            final TreeNode<E> node = queue.dequeue();
            System.out.printf("%s ", node.getElement());
            if (node.getLeftNode() != null) {
                queue.enqueue(node.getLeftNode());
            }
            if(node.getRightNode() != null) {
                queue.enqueue(node.getRightNode());
            }
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

    public TreeNode<E> search(E element) {
        return search(element, getRoot());
    }

    private TreeNode<E> search(E element, TreeNode<E> node) {
        if (node != null) {
            if (node.getElement().equals(element)) {
                return node;
            } else if (element.compareTo(node.getElement()) < 0){
                return search(element, node.getLeftNode());
            } else {
                return search(element, node.getRightNode());
            }
        } else {
            return null;
        }
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public static void main(String [] args) {
        final Tree<Integer> tree = new Tree<>();
        tree.insert(49);
        tree.insert(28);
        tree.insert(83);
        tree.insert(18);
        tree.insert(40);
        tree.insert(71);
        tree.insert(97);
        tree.insert(11);
        tree.insert(19);
        tree.insert(32);
        tree.insert(44);
        tree.insert(69);
        tree.insert(72);
        tree.insert(92);
        tree.insert(99);
        tree.output();
        final TreeNode<Integer> node1 = tree.search(49);
        System.out.printf("Element %d node is: %s%n", 49, node1 != null ? node1 : "null");
        final TreeNode<Integer> node2 = tree.search(18);
        System.out.printf("Element %d node is: %s%n", 18, node2 != null ? node2 : "null");
        final TreeNode<Integer> node3 = tree.search(97);
        System.out.printf("Element %d node is: %s%n", 97, node3 != null ? node3 : "null");
        final TreeNode<Integer> node4 = tree.search(11);
        System.out.printf("Element %d node is: %s%n", 11, node4 != null ? node4 : "null");
        final TreeNode<Integer> node5 = tree.search(99);
        System.out.printf("Element %d node is: %s%n", 99, node5 != null ? node4 : "null");
        final TreeNode<Integer> node6 = tree.search(100);
        System.out.printf("Element %d node is: %s%n", 100, node6 != null ? node5 : "null");
    }



}
