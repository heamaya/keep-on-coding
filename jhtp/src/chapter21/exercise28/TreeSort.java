package chapter21.exercise28;

import chapter21.datastructures.Tree;

import java.util.stream.IntStream;

public class TreeSort {

    public static void main(String [] args) {
        final Tree<Integer> ascendingTree = new Tree<>();
        IntStream.rangeClosed(1, 20)
            .forEach(i -> ascendingTree.insert(i));
        ascendingTree.output();
        System.out.println("___________________________________________________");
        final Tree<Integer> descendingTree = new Tree<>();
        IntStream.rangeClosed(1, 20)
            .forEach(i -> descendingTree.insert(i));
        descendingTree.output();
        System.out.println("___________________________________________________");
    }
}
