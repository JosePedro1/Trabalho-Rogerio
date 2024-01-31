import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int quantidadeCPUs = 4;
        int impressoras = 2;
        int scanner = 1;
        int CDs = 2;
        int tamanhoMemoriaPrincipal = 8192;

        Recursos recursos = new Recursos(quantidadeCPUs, impressoras, scanner, CDs, tamanhoMemoriaPrincipal);
        Escalonador escalonador = new Escalonador();

        Despachante despachante = new Despachante(escalonador, recursos);

        String caminhoArquivo = "C:\\Users\\jerem\\OneDrive\\Área de Trabalho\\Estudo\\escalanor de processo\\escalonamento.txt";

        try {
            List<Processo> listaProcessos = despachante.lerListaProcessos(caminhoArquivo);

            System.out.println("Lista de processos lida:");
            for (Processo processo : listaProcessos) {
                System.out.println(processo);
            }

            despachante.inicializarSistema(listaProcessos);

            // System.out.println("Executando o sistema:");
            despachante.executarSistema();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
