public class InterfaceSistema {
    public static void exibirInformacoes(Processo processo, int tempoAtual) {
        System.out.println("Tempo Atual: " + tempoAtual);
        System.out.println("Estado do Processo " + processo.getId() + ": " + obterNomeEstado(processo.getEstado()));
        System.out.println("Processo " + processo.getId() + ": " + processo);
        System.out.println("----------------------------------------");
    }

    private static String obterNomeEstado(int estado) {
        switch (estado) {
            case Processo.ESTADO_NOVO:
                return "Novo";
            case Processo.ESTADO_EXECUTANDO:
                return "Executando";
            case Processo.ESTADO_SUSPENSO:
                return "Suspenso";
            case Processo.ESTADO_TERMINADO:
                return "Terminado";
            default:
                return "Desconhecido";
        }
    }
}
