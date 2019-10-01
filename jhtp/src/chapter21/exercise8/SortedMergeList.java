package chapter21.exercise8;

import chapter21.datastructures.List;
import chapter21.datastructures.ListNode;
import chapter21.exercise7.SortedList;

public class SortedMergeList<E extends Comparable<E>> extends SortedList<E> {

    public SortedMergeList() {
        super("SortedMergeList");
    }

    public SortedMergeList(String name) {
        super(name);
    }

    public void merge(SortedList<E> list) {
        ListNode<E> currentNode = list.getFirstNode();
        while (currentNode != null) {
            insert(currentNode.getElement());
            currentNode = currentNode.getNextNode();
        }
    }

    public static void main(String [] args) {
        final SortedMergeList<Integer> sortedMergeList1 = new SortedMergeList<>("SortedMergeList1");
        sortedMergeList1.insert(5);
        sortedMergeList1.insert(0);
        sortedMergeList1.insert(1);
        sortedMergeList1.print();
        System.out.println();
        final SortedMergeList<Integer> sortedMergeList2 = new SortedMergeList<>("SortedMergeList2");
        sortedMergeList2.insert(3);
        sortedMergeList2.insert(2);
        sortedMergeList2.insert(4);
        sortedMergeList2.print();
        System.out.println();
        sortedMergeList1.merge(sortedMergeList2);
        System.out.println("After merging...");
        sortedMergeList1.print();
        System.out.println();
        final SortedMergeList<Integer> sortedMergeList3 = new SortedMergeList<>("SortedMergeList3");
        sortedMergeList3.insert(2);
        sortedMergeList3.insert(3);
        sortedMergeList3.insert(1);
        sortedMergeList3.print();
        System.out.println();
        final SortedMergeList<Integer> sortedMergeList4 = new SortedMergeList<>("SortedMergeList4");
        sortedMergeList4.insert(1);
        sortedMergeList4.insert(3);
        sortedMergeList4.insert(2);
        sortedMergeList4.print();
        System.out.println();
        sortedMergeList3.merge(sortedMergeList4);
        System.out.println("After merging...");
        sortedMergeList3.print();
    }
}
