public enum Status {
    FALTA_JOGADOR(-2),
    ERRO(-1),
    NAO_EH_VEZ(0),
    EH_VEZ(1),
    VENCEDOR(2),
    PERDEDOR(3),
    EMPATE(4),
    VENCEDOR_WO(5),
    PERDEDOR_WO(6);

    private final int status;

    Status(int status) {
        this.status = status;
    }

    public int getValue() {
        return status;
    }
}
