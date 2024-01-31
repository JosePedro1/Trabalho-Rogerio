import java.io.IOException;
import java.util.List;

public class Despachante {
    private Escalonador escalonador;
    private Recursos recursos;

    public Despachante(Escalonador escalonador, Recursos recursos) {
        this.escalonador = escalonador;
        this.recursos = recursos;
    }

    public List<Processo> lerListaProcessos(String caminhoArquivo) throws IOException {
        return LeitorListaProcessos.lerListaProcessos(caminhoArquivo);
    }

    public void inicializarSistema(List<Processo> listaProcessos) {
        for (Processo processo : listaProcessos) {
            escalonador.adicionarProcesso(processo);
        }
    }

    // public void imprimirInfoProcessosAposExecucao() {
    //     escalonador.imprimirInfoProcessosAposExecucao();
    // }

    public void executarSistema() {
        while (!escalonador.todosProcessosConcluidos() && (escalonador.existeProcessoTempoReal() || escalonador.existeProcessoUsuario())) {
            Processo processoAtual = escalonador.proximoProcesso();
    
            if (processoAtual != null) {
                boolean alocado = recursos.alocarRecursos(processoAtual);
    
                if (alocado) {
                    processoAtual.mudarEstado(Processo.ESTADO_EXECUTANDO);
    
                    for (int i = 0; i < processoAtual.tempoCPU; i++) {
                        recursos.atualizarRecursos(processoAtual);
                        escalonador.avancarTempo();
                        
                        // Adiciona chamada para a interface aqui
                        InterfaceSistema.exibirInformacoes(processoAtual, escalonador.getTempoAtual());
                    }
    
                    processoAtual.mudarEstado(Processo.ESTADO_TERMINADO);
                    recursos.liberarRecursos(processoAtual);
                    
                    // Adiciona informações sobre o processo após a execução
                    System.out.println("Processo " + processoAtual.getId() + ": chegada no momento " +
                            processoAtual.getTempoChegada() + ", prioridade " +
                            processoAtual.getPrioridade() + " (" +
                            processoAtual.obterNomePrioridade() + "), duração de " +
                            processoAtual.getTempoCPU() + " segundo(s) de CPU e memória de " +
                            processoAtual.getMemoria() + " MBytes, " +
                            processoAtual.obterRecursos() + ".");
    
                    escalonador.adicionarProcesso(processoAtual);
                } else {
                    processoAtual.mudarEstado(Processo.ESTADO_SUSPENSO);
                    System.out.println("[LOG] A alocação de recursos falhou para o Processo " + processoAtual.getId());
                }
            }
        }
    }
}
