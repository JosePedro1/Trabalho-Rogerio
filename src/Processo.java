public class Processo {
    public static final int ESTADO_NOVO = 0;
    public static final int ESTADO_EXECUTANDO = 1;
    public static final int ESTADO_SUSPENSO = 2;
    public static final int ESTADO_TERMINADO = 3;

    int id;
    int chegada;
    int prioridade;
    int tempoCPU;
    int memoria;
    int impressoras;
    int scanners;
    int modems;
    int CDs;
    int estado;

    public Processo(int id, int chegada, int prioridade, int tempoCPU, int memoria,
                    int impressoras, int scanners, int modems, int CDs) {
        this.id = id;
        this.chegada = chegada;
        this.prioridade = prioridade;
        this.tempoCPU = tempoCPU;
        this.memoria = memoria;
        this.impressoras = impressoras;
        this.scanners = scanners;
        this.modems = modems;
        this.CDs = CDs;
        this.estado = ESTADO_NOVO;
    }

    public void mudarEstado(int novoEstado) {
        this.estado = novoEstado;
        System.out.println("Processo " + id + " mudou para o estado: " + obterNomeEstado());
    }

    private String obterNomeEstado() {
        switch (estado) {
            case ESTADO_NOVO:
                return "Novo";
            case ESTADO_EXECUTANDO:
                return "Executando";
            case ESTADO_SUSPENSO:
                return "Suspenso";
            case ESTADO_TERMINADO:
                return "Terminado";
            default:
                return "Desconhecido";
        }
    }

    public boolean isTempoReal() {
        return prioridade == 0;
    }

    @Override
    public String toString() {
        return String.format("Processo %d: chegada no momento %d, prioridade %d (%s), duração de %d segundos " +
                "de CPU e memória de %d MBytes, %s.",
                id, chegada, prioridade, obterNomePrioridade(), tempoCPU, memoria, obterRecursos());
    }

    String obterRecursos() {
        return "sem necessidade de recursos de E/S";
    }

    String obterNomePrioridade() {
        return isTempoReal() ? "tempo real" : "usuário";
    }

    public String obterDescritor() {
        return String.format("ID: %d, Prioridade: %d, CPU: %d s, Memória: %d MB, Impressoras: %d, Scanners: %d, Modems: %d, CDs: %d",
                id, prioridade, tempoCPU, memoria, impressoras, scanners, modems, CDs);
    }

    public String getTempoChegada() {
        return Integer.toString(chegada);
    }

    public String getPrioridade() {
        return Integer.toString(prioridade);
    }

    public int getTempoCPU() {
        return tempoCPU;
    }

    public String getMemoria() {
        return Integer.toString(memoria);
    }

    public String getRecursos() {
        return obterRecursos();
    }

    public String getId() {
        return Integer.toString(id);
    }

    public String getImpressoras() {
        return Integer.toString(impressoras);
    }
    
    public String getScanners() {
        return Integer.toString(scanners);
    }
    
    public String getModems() {
        return Integer.toString(modems);
    }
    
    public String getCDs() {
        return Integer.toString(CDs);
    }

    public int getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }
    
}
