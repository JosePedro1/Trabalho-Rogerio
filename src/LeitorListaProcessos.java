import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorListaProcessos {
    public static List<Processo> lerListaProcessos(String arquivo) throws IOException {
        List<Processo> processos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int id = 1;

            while ((linha = leitor.readLine()) != null) {
                try {
                    String[] dados = linha.trim().split("\\s*,\\s*");

                    if (dados.length == 8) {
                        int chegada = Integer.parseInt(dados[0]);
                        int prioridade = Integer.parseInt(dados[1]);
                        int tempoCPU = Integer.parseInt(dados[2]);
                        int memoria = Integer.parseInt(dados[3]);
                        int impressoras = Integer.parseInt(dados[4]);
                        int scanners = Integer.parseInt(dados[5]);
                        int modems = Integer.parseInt(dados[6]);

                        Processo processo = new Processo(id++, chegada, prioridade, tempoCPU, memoria, impressoras, scanners, modems, 0);
                        processos.add(processo);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter número: " + linha);
                }
            }
        }

        return processos;
    }
}
