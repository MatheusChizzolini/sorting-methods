package file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Record {
    public final int SIZE = 1022;
    private int value;
    private char[] garbage = new char[SIZE];

    public Record() {
    }

    public Record(int value) {
        this.value = value;
        for (int i = 0; i < SIZE; i++) {
            garbage[i] = 'X';
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void write(RandomAccessFile file) {
        try {
            file.writeInt(value);
            for (int i = 0; i < SIZE; i++) {
                file.writeChar(garbage[i]);
            }
        } catch (IOException ignored) {}
    }

    public void read(RandomAccessFile file) {
        try {
            value = file.readInt();
            for (int i = 0; i < SIZE; i++) {
                garbage[i] = file.readChar();
            }
        } catch (IOException ignored) {}
    }

    public static int length() {
        return 2048;
    }
}
