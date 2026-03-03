package linkedlist;

public class List {
    private Node start, end;

    public List() {
    }

    public void addLast(int value) {
        Node node = new Node(value, end, null);
        if (start == null) {
            start = end = node;
        } else {
            end.setNext(node);
            end = node;
        }
    }

    public void printList() {
        Node current = start;
        while (current != null) {
            if (current.getNext() == null) {
                System.out.println(current.getValue());
            } else {
                System.out.print(current.getValue() + ", ");
            }

            current = current.getNext();
        }
    }

    public void insertionSort() {
        Node current = start.getNext();
        while (current != null) {
            int value = current.getValue();
            Node aux = current;
            while (aux != start && value < aux.getPrev().getValue()) {
                aux.setValue(aux.getPrev().getValue());
                aux = aux.getPrev();
            }

            aux.setValue(value);
            current = current.getNext();
        }
    }

    public void selectionSort() {
        Node current = start;
        while (current.getNext() != null) {
            int min = current.getValue();
            Node minPosition = current;
            Node aux = current.getNext();
            while (aux != null) {
                if (aux.getValue() < min) {
                    min = aux.getValue();
                    minPosition = aux;
                }

                aux = aux.getNext();
            }

            minPosition.setValue(current.getValue());
            current.setValue(min);
            current = current.getNext();
        }
    }
}
