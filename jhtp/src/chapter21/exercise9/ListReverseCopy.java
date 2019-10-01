package chapter21.exercise9;

import chapter21.datastructures.List;
import chapter21.datastructures.ListNode;
import chapter21.datastructures.Stack;

public class ListReverseCopy {

    public static <E> List<E> reverseCopy(List<E> list) {
        final List<E> listCopy = new List<>(list.getName() + "Copy");
        final Stack<E> stack = new Stack<>();
        ListNode<E> currentNode = list.getFirstNode();
        while (currentNode != null) {
            stack.push(currentNode.getElement());
            currentNode = currentNode.getNextNode();
        }
        while (!stack.isEmpty()) {
            listCopy.insertAtBack(stack.pop());
        }
        return listCopy;
    }

    public static void main(String [] args) {
        final List<Integer> linkedList1 = new List<>("LinkedList1");
        linkedList1.insertAtBack(1);
        linkedList1.insertAtBack(2);
        linkedList1.insertAtBack(3);
        linkedList1.print();
        System.out.println();
        final List<Integer> linkedList1Copy = reverseCopy(linkedList1);
        linkedList1Copy.print();
    }
}
