import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Escalonador {
    private List<Processo> listaProcessos;
    private int tempoAtual;
    private List<String> resultadoExecucao;
    private Set<Processo> processosConcluidos;

    public Escalonador() {
        this.listaProcessos = new ArrayList<>();
        this.tempoAtual = 0;
        this.resultadoExecucao = new ArrayList<>();
        this.processosConcluidos = new HashSet<>();
    }

    public void adicionarProcesso(Processo processo) {
        listaProcessos.add(processo);
    }

    public void avancarTempo() {
        tempoAtual++;
    }

    private void verificarEntrada() {
        for (Processo processo : listaProcessos) {
            if (processo.getTempoChegada().equals(Integer.toString(tempoAtual))) {
                if (processo.isTempoReal()) {
                    resultadoExecucao.add(processo.obterDescritor());
                    processosConcluidos.add(processo);
                } else {
                    resultadoExecucao.add("Processo " + processo.getId() +
                            ": chegada no momento " + tempoAtual +
                            ", prioridade " + processo.getPrioridade() +
                            " (" + processo.obterNomePrioridade() + "), duração de " +
                            processo.getTempoCPU() + " segundo(s) de CPU e memória de " +
                            processo.getMemoria() + " MBytes, " +
                            processo.obterRecursos() + ".");
                }
            }
        }
        listaProcessos.removeIf(processo -> processosConcluidos.contains(processo));
    }

    public void executarSistema() {
        while (!listaProcessos.isEmpty()) {
            verificarEntrada();
            avancarTempo();
        }
        // Remova a chamada abaixo para evitar a exceção
        // imprimirInfoProcessosAposExecucao();
    }

    // Adicione este método para corrigir a exceção
    // public void imprimirInfoProcessosAposExecucao() {
    //     System.out.println("Informações sobre os processos após a execução do sistema:");

    //     for (String resultado : resultadoExecucao) {
    //         System.out.println(resultado);
    //     }
    // }

    public void limparFilas() {
        listaProcessos.clear();
        tempoAtual = 0;
        processosConcluidos.clear();
        resultadoExecucao.clear();
    }

    public boolean todosProcessosConcluidos() {
        return listaProcessos.isEmpty();
    }

    public Processo proximoProcesso() {
        // Implemente a lógica para obter o próximo processo a ser executado
        return null;
    }

    public boolean existeProcessoTempoReal() {
        // Implemente a lógica para verificar se há processos de tempo real na lista
        return false;
    }

    public boolean existeProcessoUsuario() {
        // Implemente a lógica para verificar se há processos de usuário na lista
        return false;
    }

    public void adicionarProcessoConcluido(Processo processoAtual) {
        // Implemente a lógica para adicionar um processo à lista de processos concluídos
    }

    // Adicione este método para obter o tempo atual
    public int getTempoAtual() {
        return tempoAtual;
    }
}
