package linkedlist;

public class LinkedListMain {
    public static void execute() {
        List list = new List();

        list.addLast(7);
        list.addLast(8);
        list.addLast(12);
        list.addLast(4);
        list.addLast(2);

        list.quickSortWithPivot();
        list.printList();
    }
}
