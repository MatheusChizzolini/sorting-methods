package file;

public class FileMain {
    public static void execute() {
        File file = new File("file.dat");
        file.truncate(0);

        file.addLast(new Record(23));
        file.addLast(new Record(17));
        file.addLast(new Record(8));
        file.addLast(new Record(15));
        file.addLast(new Record(9));
        file.addLast(new Record(12));
        file.addLast(new Record(19));
        file.addLast(new Record(7));
        file.seek(0);

        file.mergeSortFirst();
        file.printFile();
    }
}
