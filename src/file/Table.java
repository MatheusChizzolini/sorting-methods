package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Table {
    private BufferedWriter writer;
    private final String divider = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";

    public Table(String fileName, boolean append) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName, append));
        if (!append) {
            writeHeader();
        }
    }

    private void writeHeader() throws IOException {
        writer.write(divider);

        writer.write(String.format("%-20s | %-54s | %-54s | %-54s |%n",
                "Métodos Ordenação",
                "Arquivo Ordenado",
                "Arquivo em Ordem Reversa",
                "Arquivo Randômico"));

        writer.write(String.format("%-20s | %-10s %-10s %-10s %-10s %-9s | %-10s %-10s %-10s %-10s %-9s | %-10s %-10s %-10s %-10s %-9s |%n",
                "",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog.", "Mov.Equa.", "Tempo",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog.", "Mov.Equa.", "Tempo",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog.", "Mov.Equa.", "Tempo"));

        writer.write(divider);
    }

    public void writeRow(String method,
                         int compOrd, int compEqOrd, int movOrd, int movEqOrd, int tempoOrd,
                         int compRev, int compEqRev, int movRev, int movEqRev, int tempoRev,
                         int compRand, int compEqRand, int movRand, int movEqRand, int tempoRand) throws IOException {
        writer.write(String.format(
                "%-20s %-10d %-10d %-10d %-10d %-10d  %-10d %-10d %-10d %-10d %-10d  %-10d %-10d %-10d %-10d %-10d%n",
                method, compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                compRev, compEqRev, movRev, movEqRev, tempoRev,
                compRand, compEqRand, movRand, movEqRand, tempoRand));

        writer.write(divider);
    }

    public void close() throws IOException {
        writer.close();
    }
}
