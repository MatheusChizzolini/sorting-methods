package file;

public class FileMain {
    public static void execute() {
        File file = new File("file.dat");
        file.truncate(0);

        file.addLast(new Record(5));
        file.addLast(new Record(6));
        file.addLast(new Record(8));
        file.addLast(new Record(7));
        file.addLast(new Record(11));
        file.addLast(new Record(10));
        file.addLast(new Record(9));
        file.seek(0);

        file.heapSort();
        file.printFile();
    }
}
