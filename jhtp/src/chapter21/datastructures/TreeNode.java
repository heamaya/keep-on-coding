package chapter21.datastructures;

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
        }
    }

    public TreeNode<E> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
