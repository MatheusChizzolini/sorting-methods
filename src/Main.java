import linkedlist.List;

public class Main {
    public static void main(String[] args) {
        List list = new List();

        list.addLast(7);
        list.addLast(8);
        list.addLast(12);
        list.addLast(4);
        list.addLast(2);

        list.selectionSort();
        list.printList();
    }
}