package file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class File {
    private String name;
    private RandomAccessFile file;

    public File(String name) {
        this.name = name;
        try {
            file = new RandomAccessFile(name, "rw");
        } catch (IOException ignored) {}
    }

    public RandomAccessFile getFile() {
        return file;
    }

    private void delete() {
        try {
            file.close();
            java.io.File javaFile = new java.io.File(name);
            javaFile.delete();
        } catch (IOException ignored) {}
    }

    public void truncate(long position) {
        try {
            file.setLength(position * Record.length());
        } catch (IOException ignored) {}
    }

    public boolean eof() {
        try {
            if (file.getFilePointer() == file.length()) {
                return true;
            }
        } catch (IOException ignored) {}

        return false;
    }

    public void seek(long position) {
        try {
            file.seek(position * Record.length());
        } catch (IOException ignored) {}
    }

    public int size() {
        try {
            return (int) file.length() / Record.length();
        } catch (IOException ignored) {}

        return -1;
    }

    public void addLast(Record record) {
        seek(size());
        record.write(file);
    }

    public void printFile() {
        seek(0);
        while (!eof()) {
            Record record = new Record();
            record.read(file);
            System.out.print(record.getValue() + " ");
        }
    }

    public void insertionSort() {
        Record record = new Record(), aux = new Record();
        for (int i = 1; i < size(); i++) {
            seek(i);
            record.read(file);

            int j = i - 1;
            seek(j);
            aux.read(file);
            while (j >= 0 && record.getValue() < aux.getValue()) {
                aux.write(file);
                j--;

                if (j >= 0) {
                    seek(j);
                    aux.read(file);
                }
            }

            if (j + 1 != i) {
                seek(j + 1);
                record.write(file);
            }
        }
    }

    private int binarySearch(int value, int i) {
        Record record = new Record();
        int start = 0, end = i - 1;
        int mid = (start + end) / 2;
        seek(mid);
        record.read(file);
        while (start < end && record.getValue() != value) {
            if (record.getValue() > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
            seek(mid);
            record.read(file);
        }

        if (value > record.getValue()) {
            return mid + 1;
        }

        return mid;
    }

    public void binaryInsertionSort() {
        Record record  = new Record(), aux = new Record();
        for (int i = 1; i < size(); i++) {
            seek(i);
            record.read(file);
            int pos = binarySearch(record.getValue(), i);
            for (int j = i; j > pos; j--) {
                seek(j - 1);
                aux.read(file);
                aux.write(file);
            }

            seek(pos);
            record.write(file);
        }
    }

    public void selectionSort() {
        int size = size();
        Record record = new Record(), aux = new Record(), min = new Record();
        for (int i = 0; i < size - 1; i++) {
            seek(i);
            record.read(file);
            int minValue = record.getValue(), minIndex = i;
            for (int j = i + 1; j < size; j++) {
                aux.read(file);
                if (aux.getValue() < minValue) {
                    minValue = aux.getValue();
                    minIndex = j;
                }
            }

            seek(minIndex);
            min.read(file);
            seek(minIndex);
            record.write(file);
            seek(i);
            min.write(file);
        }
    }

    public void bubbleSort() {
        Record record = new Record(), aux = new Record();
        int currentSize = size();
        boolean swapped = true;
        while (currentSize > 1 && swapped) {
            swapped = false;
            for (int i = 0; i < currentSize - 1; i++) {
                seek(i);
                record.read(file);
                aux.read(file);
                if (record.getValue() > aux.getValue()) {
                    seek(i);
                    aux.write(file);
                    record.write(file);
                    swapped = true;
                }
            }

            currentSize--;
        }
    }

    public void shakeSort() {
        Record record = new Record(), aux = new Record();
        int start = 0, end = size() - 1;
        boolean swapped = true;
        while (start < end && swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                seek(i);
                record.read(file);
                aux.read(file);
                if (record.getValue() > aux.getValue()) {
                    seek(i);
                    aux.write(file);
                    record.write(file);
                    swapped = true;
                }
            }

            end--;
            if (swapped) {
                swapped = false;
                for (int i = end; i > start; i--) {
                    seek(i);
                    record.read(file);
                    aux.read(file);
                    if (record.getValue() > aux.getValue()) {
                        seek(i);
                        aux.write(file);
                        record.write(file);
                        swapped = true;
                    }
                }

                start++;
            }
        }
    }

    public void heapSort() {
        int n = size();
        Record record = new Record(), aux = new Record();
        while (n > 1) {
            int parent = n / 2 - 1;
            while (parent >= 0) {
                int left = 2 * parent + 1, right = left + 1;
                int max = left;
                seek(left);
                record.read(file);
                aux.read(file);
                if (right < n && aux.getValue() > record.getValue()) {
                    max = right;
                }

                seek(max);
                record.read(file);
                seek(parent);
                aux.read(file);
                if (record.getValue() > aux.getValue()) {
                    seek(max);
                    aux.write(file);
                    seek(parent);
                    record.write(file);
                }

                parent--;
            }

            n--;
            seek(0);
            aux.read(file);
            seek(n);
            record.read(file);
            seek(0);
            record.write(file);
            seek(n);
            aux.write(file);
        }
    }

    public void shellSort() {
        Record record = new Record(), aux = new Record();
        int n = size(), dist = 1;
        while (dist < n) {
            dist = 3 * dist + 1;
        }

        dist = dist / 3;
        while (dist >= 1) {
            for (int i = dist; i < n; i++) {
                int j = i;
                seek(i);
                record.read(file);
                seek(j - dist);
                aux.read(file);
                while (j >= dist && record.getValue() < aux.getValue()) {
                    seek(j);
                    aux.write(file);
                    j -= dist;
                    if (j >= dist) {
                        seek(j - dist);
                        aux.read(file);
                    }
                }

                seek(j);
                record.write(file);
            }

            dist = dist / 3;
        }
    }

    public void quickSortWithoutPivot() {
        quickSortWithoutPivot(0, size() - 1);
    }

    private void quickSortWithoutPivot(int start, int end) {
        Record record = new Record(), aux = new Record();
        int i = start, j = end;
        boolean flag = true;
        while (i < j) {
            seek(i);
            record.read(file);
            seek(j);
            aux.read(file);
            if (flag) {
                while (i < j && record.getValue() <= aux.getValue()) {
                    i++;
                    seek(i);
                    record.read(file);
                }
            } else {
                while (i < j && aux.getValue() >= record.getValue()) {
                    j--;
                    seek(j);
                    aux.read(file);
                }
            }

            if (i < j) {
                seek(i);
                aux.write(file);
                seek(j);
                record.write(file);
                flag = !flag;
            }
        }

        if (start < i - 1) {
            quickSortWithoutPivot(start, i - 1);
        }

        if (j + 1 < end) {
            quickSortWithoutPivot(j + 1, end);
        }
    }

    private int getMaxValue() {
        int maxValue = 0;
        seek(0);
        while (!eof()) {
            Record current = new Record();
            current.read(file);
            if (current.getValue() > maxValue) {
                maxValue = current.getValue();
            }
        }

        return maxValue;
    }

    public void countingSort() {
        int maxValue = getMaxValue();
        int[] countArray = new int[maxValue + 1];
        Record current = new Record();
        seek(0);
        while (!eof()) {
            current.read(file);
            countArray[current.getValue()]++;
        }

        for (int i = 1; i <= maxValue; i++) {
            countArray[i] += countArray[i - 1];
        }

        File temp = new File("temp.dat");
        for (int i = size() - 1; i >= 0; i--) {
            seek(i);
            current.read(file);
            int value = current.getValue();
            countArray[value]--;
            int pos = countArray[value];
            temp.seek(pos);
            current.write(temp.getFile());
        }

        for (int i = 0; i < size(); i++) {
            temp.seek(i);
            current.read(temp.getFile());
            seek(i);
            current.write(file);
        }

        temp.delete();
    }

    public void gnomeSort() {
        int n = size();
        int pos = 0;
        Record current = new Record(), prev = new Record();
        while (pos < n) {
            if (pos == 0) {
                pos++;
            }

            seek(pos - 1);
            prev.read(file);
            current.read(file);
            if (current.getValue() >= prev.getValue()) {
                pos++;
            } else {
                seek(pos - 1);
                current.write(file);
                prev.write(file);
                pos--;
            }
        }
    }
}
