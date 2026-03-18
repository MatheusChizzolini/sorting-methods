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
        if (start == null) {
            return 0;
        } else {
            int count = 1;
            Node current = start;
            while (current != goal) {
                count++;
                current = current.getNext();
            }

            return count;
        }
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

    public void shakeSort() {
        Node left = start, right = end;
        boolean swapped = true;
        while (left != right && swapped) {
            swapped = false;
            Node current = left;
            while (current != right) {
                if (current.getValue() > current.getNext().getValue()) {
                    int value = current.getValue();
                    current.setValue(current.getNext().getValue());
                    current.getNext().setValue(value);
                    swapped = true;
                }

                current = current.getNext();
            }

            right = right.getPrev();
            if (swapped) {
                swapped = false;
                current = right;
                while (current != left) {
                    if (current.getValue() < current.getPrev().getValue()) {
                        int value = current.getValue();
                        current.setValue(current.getPrev().getValue());
                        current.getPrev().setValue(value);
                        swapped = true;
                    }

                    current = current.getPrev();
                }

                left = left.getNext();
            }
        }
    }

    public void heapSort() {
        Node aux = end;
        while (aux != start) {
            int length= length(aux), value;
            int parent = length / 2 - 1;
            while (parent >= 0) {
                int leftChild = 2 * parent + 1, rightChild = leftChild + 1;
                int maxChild = leftChild;
                if (rightChild < length && seek(rightChild).getValue() > seek(leftChild).getValue()) {
                    maxChild = rightChild;
                }

                Node parentNode = seek(parent), maxChildNode = seek(maxChild);
                if (maxChildNode.getValue() > parentNode.getValue()) {
                    value = maxChildNode.getValue();
                    maxChildNode.setValue(parentNode.getValue());
                    parentNode.setValue(value);
                }

                parent--;
            }

            value = start.getValue();
            start.setValue(aux.getValue());
            aux.setValue(value);
            aux = aux.getPrev();
        }
    }

    public void shellSort() {
        int distance = 1;
        while (distance < length(end)) {
            distance = 3 * distance + 1;
        }

        distance = distance / 3;
        while (distance >= 1) {
            for (int i = distance; i < length(end); i++) {
                Node current = seek(i);
                int value = current.getValue();
                int position = i;
                while (position >= distance && current.getValue() < seek(position - distance).getValue()) {
                    current.setValue(seek(position - distance).getValue());
                    position = position - distance;
                }

                seek(position).setValue(value);
            }

            distance = distance / 3;
        }
    }
}
