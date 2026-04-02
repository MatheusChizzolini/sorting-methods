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
            movEqOrd  = 3 * (N - 1);

            compEqRand = (N * N + N - 2) / 4;
            movEqRand  = (N * N + 9 * N - 10) / 4;

            compEqRev = (N * N + N - 4) / 4;
            movEqRev  = (N * N + 3 * N - 4) / 2;

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

            compEqOrd = (int) (N * (Math.log(N) - Math.log(2.71828) + 0.5));
            movEqOrd  = 3 * (N - 1);

            compEqRand = (int) (N * (Math.log(N) - Math.log(2.71828) + 0.5));
            movEqRand  = (N * N + 9 * N - 10) / 4;

            compEqRev = (int) (N * (Math.log(N) - Math.log(2.71828) + 0.5));
            movEqRev  = (N * N + 3 * N - 4) / 2;

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

            compEqOrd = (N * N - N) / 2;
            movEqOrd  = 3 * (N - 1);

            compEqRand = (N * N - N) / 2;
            movEqRand  = (int) (N * (Math.log(N) + 0.577216));

            compEqRev = (N * N - N) / 2;
            movEqRev  = (N * N) / 4 + 3 * (N - 1);

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

            compEqOrd  = (N * N - N) / 2;
            movEqOrd   = 0;

            compEqRand = (N * N - N) / 2;
            movEqRand  = 3 * (N * N - N) / 2;

            compEqRev  = (N * N - N) / 2;
            movEqRev   = 3 * (N * N - N) / 4;

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

            compEqOrd  = (N * N - N) / 2;
            movEqOrd   = 0;

            compEqRand = (N * N - N) / 2;
            movEqRand  = 3 * (N * N - N) / 2;

            compEqRev  = (N * N - N) / 2;
            movEqRev   = 3 * (N * N - N) / 4;

            table.writeRow("Shake",
                    compOrd, compEqOrd, movOrd, movEqOrd, (int) tempoOrd,
                    compRev, compEqRev, movRev, movEqRev, (int) tempoRev,
                    compRand, compEqRand, movRand, movEqRand, (int) tempoRand);

            // Heap
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.heapSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.heapSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.heapSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Heap",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            // Shell
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.shellSort();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.shellSort();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.shellSort();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Shell",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            // Quick s/ pivô
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.quickSortWithoutPivot();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.quickSortWithoutPivot();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.quickSortWithoutPivot();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Quick s/ pivô",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            // Quick c/ pivo
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.quickSortWithPivot();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.quickSortWithPivot();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.quickSortWithPivot();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Quick c/ pivô",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            // Merge 1ª implementação
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.mergeSortFirst();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.mergeSortFirst();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.mergeSortFirst();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Merge 1ª implement",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            // Merge 2ª implementação
            sortedFile.initComp();
            sortedFile.initMov();
            startTime = System.currentTimeMillis();
            sortedFile.mergeSortSecond();
            endTime = System.currentTimeMillis();
            compOrd = sortedFile.getComp();
            movOrd = sortedFile.getMov();
            tempoOrd = (endTime - startTime) / 1000;

            auxReverse.copy(reverseFile.getFile());
            auxReverse.initComp();
            auxReverse.initMov();
            startTime = System.currentTimeMillis();
            auxReverse.mergeSortSecond();
            endTime = System.currentTimeMillis();
            compRev = auxReverse.getComp();
            movRev = auxReverse.getMov();
            tempoRev = (endTime - startTime) / 1000;

            auxRandom.copy(randomFile.getFile());
            auxRandom.initComp();
            auxRandom.initMov();
            startTime = System.currentTimeMillis();
            auxRandom.mergeSortSecond();
            endTime = System.currentTimeMillis();
            compRand = auxRandom.getComp();
            movRand = auxRandom.getMov();
            tempoRand = (endTime - startTime) / 1000;

            table.writeRow("Merge 2ª implement",
                    compOrd, 0, movOrd, 0, (int) tempoOrd,
                    compRev, 0, movRev, 0, (int) tempoRev,
                    compRand, 0, movRand, 0, (int) tempoRand);

            sortedFile.delete();
            reverseFile.delete();
            randomFile.delete();
            auxReverse.delete();
            auxRandom.delete();
            table.close();
        } catch (IOException ignored) {}
    }
}
