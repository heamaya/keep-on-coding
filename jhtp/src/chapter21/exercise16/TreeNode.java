package chapter21.exercise16;

import java.security.SecureRandom;

public class TreeNode<E extends Comparable<E>> {

    private TreeNode<E> leftNode;
    private E element;
    private TreeNode rightNode;

    public TreeNode(E element) {
        this.element = element;
    }

    public void insert(E element) {
        if (element.compareTo(this.element) < 0) {
            if (leftNode == null) {
                leftNode = new TreeNode<>(element);
            }
            else {
                leftNode.insert(element);
            }
        } else if (element.compareTo(this.element) > 0) {
            if (rightNode == null) {
                rightNode = new TreeNode<>(element);
            } else {
                rightNode.insert(element);
            }
        } else {
            if (leftNode == null) {
                leftNode = new TreeNode<>(element);
            }
            else if (rightNode == null) {
                rightNode = new TreeNode<>(element);
            }
            else {
                final SecureRandom secureRandom = new SecureRandom();
                final int direction = secureRandom.nextInt(1);
                if (direction == 0) {
                    leftNode.insert(element);
                } else {
                    rightNode.insert(element);
                }
            }
        }
    }

    public TreeNode<E> getLeftNode() {
        return leftNode;
    }

    public E getElement() {
        return element;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }
}
