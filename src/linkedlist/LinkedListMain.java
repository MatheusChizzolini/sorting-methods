package linkedlist;

public class LinkedListMain {
    public static void execute() {
        List list = new List();

        list.addLast(23);
        list.addLast(17);
        list.addLast(8);
        list.addLast(15);
        list.addLast(9);
        list.addLast(12);
        list.addLast(19);
        list.addLast(7);
        list.addLast(6);
        list.addLast(11);
        list.addLast(27);
        list.addLast(3);
        list.addLast(14);
        list.addLast(2);
        list.addLast(18);
        list.addLast(7);

        list.timSort();
        list.printList();
    }
}
