package chapter21.exercise24;

import chapter21.datastructures.Queue;
import chapter21.datastructures.Tree;
import chapter21.datastructures.TreeNode;
import java.security.SecureRandom;

public class LevelTraversalTree<E extends Comparable<E>> extends Tree {

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

    public static void main(String [] args) {
        final SecureRandom secureRandom = new SecureRandom();
        final LevelTraversalTree<Integer> levelTraversalTree = new LevelTraversalTree<>();
        secureRandom.ints(16, 1, 100)
            .forEach(i -> levelTraversalTree.insert(i));
        System.out.println("Level Traversal:");
        levelTraversalTree.levelTraversal();
    }

}
