package file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class File {
    private String name;
    private RandomAccessFile file;
    private int comp, mov;

    public File(String name) {
        this.name = name;
        try {
            file = new RandomAccessFile(name, "rw");
        } catch (IOException ignored) {}
    }

    public RandomAccessFile getFile() {
        return file;
    }

    public void delete() {
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

    public void copy(RandomAccessFile source) {
        try {
            source.seek(0);
            this.truncate(0);
            Record record = new Record();
            while (source.getFilePointer() < source.length()) {
                record.read(source);
                seek(size());
                record.write(file);
            }
        } catch (IOException ignored) {}
    }

    public void printFile() {
        seek(0);
        while (!eof()) {
            Record record = new Record();
            record.read(file);
            System.out.print(record.getValue() + " ");
        }
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
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
                comp++;
                aux.write(file);
                mov++;
                j--;

                if (j >= 0) {
                    seek(j);
                    aux.read(file);
                }
            }

            comp++;
            if (j + 1 != i) {
                seek(j + 1);
                record.write(file);
                mov++;
            }
        }
    }

    private int binarySearch(int value, int i) {
        Record record = new Record();
        int start = 0, end = i - 1;
        int mid = (start + end) / 2;
        seek(mid);
        record.read(file);
        comp++;
        while (start < end && record.getValue() != value) {
            comp++;
            if (record.getValue() > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
            seek(mid);
            record.read(file);
        }

        comp++;
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
                mov++;
            }

            seek(pos);
            record.write(file);
            mov++;
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
                comp++;
                if (aux.getValue() < minValue) {
                    minValue = aux.getValue();
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                seek(minIndex);
                min.read(file);
                seek(minIndex);
                record.write(file);
                mov++;
                seek(i);
                min.write(file);
                mov++;
            }
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
                comp++;
                if (record.getValue() > aux.getValue()) {
                    seek(i);
                    aux.write(file);
                    mov++;
                    record.write(file);
                    mov++;
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
                comp++;
                if (record.getValue() > aux.getValue()) {
                    seek(i);
                    aux.write(file);
                    mov++;
                    record.write(file);
                    mov++;
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
                    comp++;
                    if (record.getValue() > aux.getValue()) {
                        seek(i);
                        aux.write(file);
                        mov++;
                        record.write(file);
                        mov++;
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
                comp++;
                if (right < n && aux.getValue() > record.getValue()) {
                    max = right;
                }

                seek(max);
                record.read(file);
                seek(parent);
                aux.read(file);
                comp++;
                if (record.getValue() > aux.getValue()) {
                    seek(max);
                    aux.write(file);
                    mov++;
                    seek(parent);
                    record.write(file);
                    mov++;
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
            mov++;
            seek(n);
            aux.write(file);
            mov++;
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
                    comp++;
                    seek(j);
                    aux.write(file);
                    mov++;
                    j -= dist;
                    if (j >= dist) {
                        seek(j - dist);
                        aux.read(file);
                    }
                }

                comp++;
                seek(j);
                record.write(file);
                mov++;
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
                    comp++;
                    i++;
                    seek(i);
                    record.read(file);
                }
            } else {
                while (i < j && aux.getValue() >= record.getValue()) {
                    comp++;
                    j--;
                    seek(j);
                    aux.read(file);
                }
            }

            comp++;
            if (i < j) {
                seek(i);
                aux.write(file);
                mov++;
                seek(j);
                record.write(file);
                mov++;
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

    public void quickSortWithPivot() {
        quickSortWithPivot(0, size() - 1);
    }

    private void quickSortWithPivot(int start, int end) {
        Record pivot = new Record(), recordI = new Record(), recordJ = new Record();
        seek((start + end) / 2);
        pivot.read(file);
        int pivotValue = pivot.getValue();
        int i = start, j = end;
        while (i < j) {
            seek(i);
            recordI.read(file);
            while (recordI.getValue() < pivotValue) {
                comp++;
                i++;
                seek(i);
                recordI.read(file);
            }

            comp++;
            seek(j);
            recordJ.read(file);
            while (recordJ.getValue() > pivotValue) {
                comp++;
                j--;
                seek(j);
                recordJ.read(file);
            }

            comp++;
            if (i <= j) {
                seek(i);
                recordJ.write(file);
                mov++;
                seek(j);
                recordI.write(file);
                mov++;
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSortWithPivot(start, j);
        }
        if (i < end) {
            quickSortWithPivot(i, end);
        }
    }

    private int getMaxValue() {
        int maxValue = 0;
        seek(0);
        while (!eof()) {
            Record current = new Record();
            current.read(file);
            comp++;
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
            mov++;
        }

        for (int i = 0; i < size(); i++) {
            temp.seek(i);
            current.read(temp.getFile());
            seek(i);
            current.write(file);
            mov++;
        }

        temp.delete();
    }

    private void countingSort(int exp) {
        int[] countArray = new int[10];
        Record current = new Record();
        seek(0);
        while (!eof()) {
            current.read(file);
            int digit = (current.getValue() / exp) % 10;
            countArray[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            countArray[i] += countArray[i - 1];
        }

        File temp = new File("temp.dat");
        for (int i = size() - 1; i >= 0; i--) {
            seek(i);
            current.read(file);
            int value = current.getValue();
            int digit = (value / exp) % 10;
            countArray[digit]--;
            int pos = countArray[digit];
            temp.seek(pos);
            current.write(temp.getFile());
            mov++;
        }

        for (int i = 0; i < size(); i++) {
            temp.seek(i);
            current.read(temp.getFile());
            seek(i);
            current.write(file);
            mov++;
        }

        temp.delete();
    }

    public void radixSort() {
        int maxValue = getMaxValue();
        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countingSort(exp);
        }
    }

    public void bucketSort() {
        int k = (int) Math.sqrt(size());
        int maxValue = getMaxValue();
        int limit = (maxValue / k) + 1;
        File[] buckets = new File[k];
        for (int i = 0; i < k; i++) {
            buckets[i] = new File("bucket" + i + ".dat");
            buckets[i].truncate(0);
        }

        seek(0);
        Record current = new Record();
        while (!eof()) {
            current.read(file);
            int index = current.getValue() / limit;
            buckets[index].addLast(new Record(current.getValue()));
        }

        for (int i = 0; i < k; i++) {
            buckets[i].insertionSort();
        }

        seek(0);
        Record aux = new Record();
        for (int i = 0; i < k; i++) {
            buckets[i].seek(0);
            while (!buckets[i].eof()) {
                aux.read(buckets[i].getFile());
                aux.write(file);
                mov++;
            }
        }

        for (int i = 0; i < k; i++) {
            buckets[i].delete();
        }
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
            comp++;
            if (current.getValue() >= prev.getValue()) {
                pos++;
            } else {
                seek(pos - 1);
                current.write(file);
                mov++;
                prev.write(file);
                mov++;
                pos--;
            }
        }
    }

    public void combSort() {
        int n = size();
        int gap = n;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            swapped = false;
            gap = (gap * 10) / 13;
            if (gap < 1) {
                gap  = 1;
            }

            Record current = new Record(), aux = new Record();
            for (int i = 0; i < n - gap; i++) {
                seek(i);
                current.read(file);
                seek(i + gap);
                aux.read(file);
                comp++;
                if (current.getValue() > aux.getValue()) {
                    seek(i);
                    aux.write(file);
                    mov++;
                    seek(i + gap);
                    current.write(file);
                    mov++;
                }
            }
        }
    }

    private void partition(File temp1, File temp2) {
        int n = size();
        int mid = n / 2;
        seek(0);
        Record current = new Record();
        for (int i = 0; i < mid; i++) {
            current.read(file);
            temp1.addLast(new Record(current.getValue()));
        }

        for (int i = mid; i < n; i++) {
            current.read(file);
            temp2.addLast(new Record(current.getValue()));
        }
    }

    private void merge(File temp1, File temp2, int seq) {
        int i = 0, j = 0, fixedSeq = seq;
        seek(0);
        Record aux1 = new Record();
        Record aux2 = new Record();
        while (!eof()) {
           while (i < seq && j < seq) {
               temp1.seek(i);
               aux1.read(temp1.getFile());
               temp2.seek(j);
               aux2.read(temp2.getFile());
               comp++;
               if (aux1.getValue() < aux2.getValue()) {
                   aux1.write(file);
                   mov++;
                   i++;
               } else {
                   aux2.write(file);
                   mov++;
                   j++;
               }
           }

           while (i < seq) {
               temp1.seek(i);
               aux1.read(temp1.getFile());
               aux1.write(file);
               mov++;
               i++;
           }

           while (j < seq) {
               temp2.seek(j);
               aux2.read(temp2.getFile());
               aux2.write(file);
               mov++;
               j++;
           }

           seq += fixedSeq;
        }
    }

    public void mergeSortFirst() {
        int seq = 1, n = size();
        File temp1 = new File("temp1.dat");
        File temp2 = new File("temp2.dat");
        while (seq < n) {
            temp1.truncate(0);
            temp2.truncate(0);
            partition(temp1, temp2);
            merge(temp1, temp2, seq);
            seq *= 2;
        }

        temp1.delete();
        temp2.delete();
    }

    public void mergeSortSecond() {
        File temp = new File("temp.dat");
        mergeSortSecond(0, size() - 1, temp);
        temp.delete();
    }

    private void mergeSortSecond(int left, int right, File temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortSecond(left, mid, temp);
            mergeSortSecond(mid + 1, right, temp);
            merge(left, mid, mid + 1, right, temp);
        }
    }

    private void merge(int start1, int end1, int start2, int end2, File temp) {
        int i = start1, j = start2, k = 0;
        Record auxI = new Record(), auxJ = new Record();
        seek(i);
        auxI.read(file);
        seek(j);
        auxJ.read(file);
        while (i <= end1 && j <= end2) {
            temp.seek(k);
            comp++;
            if (auxI.getValue() < auxJ.getValue()) {
                auxI.write(temp.getFile());
                mov++;
                i++;
                if (i <= end1) {
                    seek(i);
                    auxI.read(file);
                }
            } else {
                auxJ.write(temp.getFile());
                mov++;
                j++;
                if (j <= end2) {
                    seek(j);
                    auxJ.read(file);
                }
            }
            k++;
        }

        while (i <= end1) {
            seek(i);
            auxI.read(file);
            temp.seek(k);
            auxI.write(temp.getFile());
            mov++;
            i++;
            k++;
        }

        while (j <= end2) {
            seek(j);
            auxJ.read(file);
            temp.seek(k);
            auxJ.write(temp.getFile());
            mov++;
            j++;
            k++;
        }

        for (i = 0; i < k; i++) {
            temp.seek(i);
            auxI.read(temp.getFile());
            seek(start1 + i);
            auxI.write(file);
            mov++;
        }
    }

    private void insertionSort(int start, int end) {
        if (start < end) {
            Record record = new Record(), aux = new Record();
            for (int i = start + 1; i <= end; i++) {
                seek(i);
                record.read(file);

                int j = i - 1;
                seek(j);
                aux.read(file);
                while (j >= start && record.getValue() < aux.getValue()) {
                    comp++;
                    aux.write(file);
                    mov++;
                    j--;

                    if (j >= start) {
                        seek(j);
                        aux.read(file);
                    }
                }

                comp++;
                if (j + 1 != i) {
                    seek(j + 1);
                    record.write(file);
                    mov++;
                }
            }
        }
    }

    public void timSort() {
        int n = size();
        final int RUN = 32;
        for (int i = 0; i < n; i += RUN) {
            int end = Math.min((i + RUN - 1), (n - 1));
            insertionSort(i , end);
        }

        File temp = new File("temp.dat");

        for (int i = RUN; i < n; i = 2 * i) {
            for (int start = 0; start < n; start += 2 * i) {
                int mid = start + i - 1;
                int end = Math.min((start + 2 * i - 1), (n - 1));
                if (mid < end) {
                    merge(start, mid, mid + 1, end, temp);
                }
            }
        }

        temp.delete();
    }

    public void generateSortedFile(int N) {
        this.truncate(0);
        for (int i = 1; i <= N; i++) {
            this.addLast(new Record(i));
        }
    }

    public void generateReverseFile(int N) {
        this.truncate(0);
        for (int i = N; i > 0; i--) {
            this.addLast(new Record(i));
        }
    }

    public void generateRandomFile(int N) {
        this.truncate(0);
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            this.addLast(new Record(random.nextInt(1000)));
        }
    }
}
