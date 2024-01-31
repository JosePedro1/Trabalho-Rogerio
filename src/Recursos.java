public class Recursos {
    private int impressorasDisponiveis;
    private int scannerDisponivel;
    private int modemDisponivel;
    private int CDsDisponiveis;
    private int memoriaPrincipalDisponivel;

    public Recursos(int impressoras, int scanner, int modems, int CDs, int tamanhoMemoriaPrincipal) {
        this.impressorasDisponiveis = impressoras;
        this.scannerDisponivel = scanner;
        this.modemDisponivel = modems;
        this.CDsDisponiveis = CDs;
        this.memoriaPrincipalDisponivel = tamanhoMemoriaPrincipal;
    }

    public boolean alocarRecursos(Processo processo) {
        if (processo.isTempoReal()) {
            // Processos de tempo real só precisam de memória
            if (processo.memoria <= memoriaPrincipalDisponivel) {
                memoriaPrincipalDisponivel -= processo.memoria;
                return true;
            }
        } else {
            // Processos de usuários podem precisar de outros recursos
            if (processo.impressoras <= impressorasDisponiveis &&
                processo.scanners <= scannerDisponivel &&
                processo.modems <= modemDisponivel &&
                processo.CDs <= CDsDisponiveis &&
                processo.memoria <= memoriaPrincipalDisponivel) {

                impressorasDisponiveis -= processo.impressoras;
                scannerDisponivel -= processo.scanners;
                modemDisponivel -= processo.modems;
                CDsDisponiveis -= processo.CDs;
                memoriaPrincipalDisponivel -= processo.memoria;

                return true;
            }
        }

        System.out.println("[LOG] A alocação de recursos falhou para o Processo " + processo.getId());
        return false;
    }

    public void liberarRecursos(Processo processo) {
        if (!processo.isTempoReal()) {
            impressorasDisponiveis += processo.impressoras;
            scannerDisponivel += processo.scanners;
            modemDisponivel += processo.modems;
            CDsDisponiveis += processo.CDs;
        }

        memoriaPrincipalDisponivel += processo.memoria;
    }

    public void atualizarRecursos(Processo processoAtual) {
        // Implemente a lógica de atualização de recursos conforme necessário
        // Este método pode ser vazio ou conter ações específicas de atualização de recursos
    }
}
