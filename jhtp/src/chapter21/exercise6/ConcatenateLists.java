package chapter21.exercise6;

import chapter21.datastructures.List;

public class ConcatenateLists {

    public static <E> void concatenate(List<E> list1, List list2) {
        list1.getLastNode().setNextNode(list2.getFirstNode());
        list1.setLastNode(list2.getLastNode());
    }

    public static void main(String [] args) {
        final List<Character> list1 = new List<>("LinkedList1");
        list1.insertAtBack('H');
        list1.insertAtBack('e');
        list1.insertAtBack('l');
        list1.insertAtBack('l');
        list1.insertAtBack('o');
        list1.insertAtBack(' ');
        list1.print();
        final List<Character> list2 = new List<>("LinkedList2");
        list2.insertAtBack('w');
        list2.insertAtBack('o');
        list2.insertAtBack('r');
        list2.insertAtBack('l');
        list2.insertAtBack('d');
        list2.insertAtBack('!');
        list2.print();
        ConcatenateLists.concatenate(list1, list2);
        System.out.println("After concatening...");
        list1.print();
    }

}
