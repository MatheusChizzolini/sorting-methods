package linkedlist;

public class LinkedListMain {
    public static void execute() {
        List list = new List();

        list.addLast(6);
        list.addLast(7);
        list.addLast(9);
        list.addLast(4);
        list.addLast(2);

        list.gnomeSort();
        list.printList();
    }
}
