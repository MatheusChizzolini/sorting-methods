package file;

import java.io.IOException;

public class FileMain {
    public static void execute() {
        final int N = 10;
        File sortedFile = new File("sorted-file.dat");
        File reverseFile = new File("reverse-file.dat");
        File randomFile = new File("random-file.dat");

        sortedFile.generateSortedFile(N);
        reverseFile.generateReverseFile(N);
        randomFile.generateRandomFile(N);

        File auxReverse = new File("aux-reverse.dat");
        File auxRandom = new File("aux-random.dat");

        try {
            Table table = new Table("tabela.txt", false);
            long startTime, endTime;

            int compOrd, compEqOrd, movOrd, movEqOrd;
            int compRev, compEqRev, movRev, movEqRev;
            int compRand, compEqRand, movRand, movEqRand;
            long tempoOrd, tempoRev, tempoRand;

            // Inserção Direta
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.insertionSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.insertionSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.insertionSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            compEqOrd = N - 1;
            movEqOrd = 0;
            compEqRev = N * (N - 1) / 2;
            movEqRev = N * (N - 1) / 2;
            compEqRand = N * (N - 1) / 4;
            movEqRand = N * (N - 1) / 4;

            table.writeRow("Inserção Direta",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            // Inserção Binária
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.binaryInsertionSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.binaryInsertionSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.binaryInsertionSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            compEqOrd = (int) (N * (Math.log(N) / Math.log(2)));
            movEqOrd = 0;
            compEqRev = (int) (N * (Math.log(N) / Math.log(2)));
            movEqRev = N * (N - 1) / 2;
            compEqRand = (int) (N * (Math.log(N) / Math.log(2)));
            movEqRand = N * (N - 1) / 4;

            table.writeRow("Inserção Binária",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            // Seleção
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.selectionSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.selectionSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.selectionSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            compEqOrd = N * (N - 1) / 2;
            movEqOrd = 0;
            compEqRev = N * (N - 1) / 2;
            movEqRev = 2 * (N - 1);
            compEqRand = N * (N - 1) / 2;
            movEqRand = 2 * (N - 1);

            table.writeRow("Seleção",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            // Bolha
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.bubbleSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.bubbleSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.bubbleSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            compEqOrd  = N - 1;
            movEqOrd   = 0;
            compEqRev  = N * (N - 1) / 2;
            movEqRev   = N * (N - 1);
            compEqRand = N * (N - 1) / 4;
            movEqRand  = N * (N - 1) / 2;

            table.writeRow("Bolha",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            // Shake
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.shakeSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.shakeSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.shakeSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            compEqOrd = N - 1;
            movEqOrd = 0;
            compEqRev = N * (N - 1) / 2;
            movEqRev = N * (N - 1);
            compEqRand = N * (N - 1) / 4;
            movEqRand = N * (N - 1) / 2;

            table.writeRow("Shake",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            sortedFile.delete();
            reverseFile.delete();
            randomFile.delete();
            auxReverse.delete();
            auxRandom.delete();
            table.close();
        } catch (IOException ignored) {}
    }
}
