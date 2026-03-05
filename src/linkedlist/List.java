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

    private int length(Node goal) {
        int count = 0;
        Node current = start;
        while (current != goal) {
            count++;
            current = current.getNext();
        }

        return count;
    }

    private Node seek(int index) {
        Node current = start;
        while (current != null && index > 0) {
            index--;
            current = current.getNext();
        }

        return current;
    }

    private Node binarySearch(int value, Node current) {
        int start = 0;
        int end = length(current) - 1;
        int midIndex = (start + end) / 2;
        Node mid = seek(midIndex);
        while (start <= end) {
            if (value > mid.getValue()) {
                start = midIndex + 1;
            } else {
                end = midIndex - 1;
            }

            midIndex = (start + end) / 2;
            mid = seek(midIndex);
        }

        return seek(start);
    }

    public void binaryInsertionSort() {
        Node current = start.getNext();
        while (current != null) {
            int value = current.getValue();
            Node position = binarySearch(value, current);
            Node aux = current;
            while (aux != position) {
                aux.setValue(aux.getPrev().getValue());
                aux = aux.getPrev();
            }

            position.setValue(value);
            current = current.getNext();
        }
    }

    public void bubbleSort() {
        Node aux = end;
        boolean hasChanged = true;
        while (aux != start && hasChanged) {
            hasChanged = false;
            Node current = start;
            while (current.getNext() != null) {
                if (current.getValue() > current.getNext().getValue()) {
                    int value = current.getValue();
                    current.setValue(current.getNext().getValue());
                    current.getNext().setValue(value);
                    hasChanged = true;
                }

                current = current.getNext();
            }

            aux = aux.getPrev();
        }
    }
}
