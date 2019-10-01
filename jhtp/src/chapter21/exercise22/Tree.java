package chapter21.exercise22;

import chapter21.datastructures.Queue;
import chapter21.datastructures.TreeNode;

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

    private TreeNode<E> searchParent(E element, TreeNode<E> node) {
        if (node != null) {
            if (node.getLeftNode() != null && node.getLeftNode().getElement().equals(element)) {
                return node;
            }
            else if (node.getRightNode() != null && node.getRightNode().getElement().equals(element)) {
                return node;
            }
            else if(element.compareTo(node.getElement()) < 0) {
                if (node.getLeftNode() != null) {
                    return searchParent(element, node.getLeftNode());
                }
                else {
                  return null;
                }
            }
            else {
                if (node.getRightNode() != null) {
                    return searchParent(element, node.getRightNode());
                }
                else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public void delete(E element) {
        final TreeNode<E> currentNode = search(element, getRoot());
        if (currentNode == null) {
            System.out.println("The node was not found!");
        } else {
            final TreeNode<E> parentNode = searchParent(element, getRoot());
            if(parentNode != null) {
                if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
                    if (parentNode.getLeftNode() != null && parentNode.getLeftNode().getElement().equals(element)) {
                        parentNode.setLeftNode(null);
                    } else {
                        parentNode.setRightNode(null);
                    }

                } else if (currentNode.getLeftNode() != null && currentNode.getRightNode() != null) {
                    replaceCurrentNode(element, currentNode, parentNode);
                }
                else if (currentNode.getLeftNode() != null) {
                    if (parentNode.getLeftNode() != null && parentNode.getLeftNode().getElement().equals(element)) {
                        parentNode.setLeftNode(currentNode.getLeftNode());
                    } else {
                        parentNode.setRightNode(currentNode.getLeftNode());
                    }
                    currentNode.setLeftNode(null);
                } else if (currentNode.getRightNode() != null) {
                    if (parentNode.getLeftNode() != null && parentNode.getLeftNode().getElement().equals(element)) {
                        parentNode.setLeftNode(currentNode.getRightNode());
                    } else {
                        parentNode.setRightNode(currentNode.getRightNode());
                    }
                    currentNode.setRightNode(null);
                }
            }
            else if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
                root = null;
            }
            else if (currentNode.getLeftNode() != null && currentNode.getRightNode() != null) {
                replaceCurrentNode(element, currentNode);
            }
            else if (currentNode.getLeftNode() != null) {
                root = root.getLeftNode();
            }
            else if (currentNode.getRightNode() != null) {
                root = root.getRightNode();
            }
        }
    }

    private void replaceCurrentNode(E element, TreeNode<E> currentNode) {
        replaceCurrentNode(element, currentNode, null);
    }
    private void replaceCurrentNode(E element, TreeNode<E> currentNode, TreeNode<E> parentNode) {
        TreeNode<E> replacementNode = currentNode.getLeftNode();
        while (replacementNode.getRightNode() != null) {
            replacementNode = replacementNode.getRightNode();
        }
        final TreeNode<E> tempCurrentNode = currentNode;
        final TreeNode<E> replacementParentNode = searchParent(replacementNode.getElement(), getRoot());
        if (parentNode != null && parentNode.getRightNode() != null && parentNode.getRightNode().getElement().equals(element)) {
            parentNode.setRightNode(replacementNode);
        }
        else if (parentNode != null) {
            parentNode.setLeftNode(replacementNode);
        } else {
            root = replacementNode;
        }
        if (replacementNode.getLeftNode() != null) {
            if (replacementParentNode.getLeftNode() != null && replacementParentNode.getLeftNode().getElement().equals(element)) {
                replacementParentNode.setLeftNode(replacementNode.getLeftNode());
            }
            else {
                replacementParentNode.setRightNode(replacementNode.getLeftNode());
            }
        }
        else if (currentNode != replacementParentNode) {
            if (replacementParentNode.getLeftNode() != null && replacementParentNode.getLeftNode().getElement().equals(element)) {
                replacementParentNode.setLeftNode(null);
            }
            else {
                replacementParentNode.setRightNode(null);
            }
        }
        replacementNode.setRightNode(tempCurrentNode.getRightNode());
        if (currentNode != replacementParentNode) {
            replacementNode.setLeftNode(tempCurrentNode.getLeftNode());
        }

    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public static void main(String [] args) {
        final Tree<Integer> tree = new Tree<>();
        create(tree);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(11);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(99);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(18);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(97);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(83);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(28);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(49);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(19);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(40);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(32);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(44);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(92);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(71);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(72);
        tree.output();
        System.out.println("___________________________________________________");
        tree.delete(69);
        tree.output();
    }

    private static void create(Tree<Integer> tree) {
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
    }

}
