public enum Direcao {
    DIREITA,
    DIAGONAL_DIREITA_INFERIOR,
    BAIXO,
    DIAGONAL_ESQUERDA_INFERIOR,
    ESQUERDA,
    DIAGONAL_ESQUERDA_SUPERIOR,
    CIMA,
    DIAGONAL_DIREITA_SUPERIOR;

    public static Direcao fromInteger(int x) {
        switch (x) {
            case 0:
                return DIREITA;
            case 1:
                return DIAGONAL_DIREITA_INFERIOR;
            case 2:
                return BAIXO;
            case 3:
                return DIAGONAL_ESQUERDA_INFERIOR;
            case 4:
                return ESQUERDA;
            case 5:
                return DIAGONAL_ESQUERDA_SUPERIOR;
            case 6:
                return CIMA;
            case 7:
                return DIAGONAL_DIREITA_SUPERIOR;
            default:
                return null;
        }
    }
}
