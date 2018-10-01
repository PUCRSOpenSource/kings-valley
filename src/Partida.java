public class Partida {

    private Tabuleiro tabuleiro;
    private Jogador j1;
    private Jogador j2;
    private boolean vezJ1;
    private boolean encerrada;
    private boolean wo;
    private Jogador vencedor;
    private long timer;

    public Partida() {
        tabuleiro = new Tabuleiro();
        vezJ1 = true;
        wo = false;
        encerrada = false;
    }

    public Jogador getJ1() {
        return j1;
    }

    public void setJ1(Jogador j1) {
        this.j1 = j1;
    }

    public Jogador getJ2() {
        return j2;
    }

    public void setJ2(Jogador j2) {
        this.j2 = j2;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public boolean passouUmMinuto() {
        if (System.currentTimeMillis() > (timer + 60 * 1000)) {
            return true;
        }
        return false;
    }

    public int getStatus(int idUsuario) {
        if (j1 == null || j2 == null)
            return Status.FALTA_JOGADOR.getValue();
        if (passouUmMinuto()) {
            encerrada = true;
            vencedor = vezJ1 ? j2 : j1;
            wo = true;
        }
        if (encerrada) {
            if (vencedor == null) {
                return Status.EMPATE.getValue();
            }
            if (vencedor.getId() == idUsuario) {
                if (wo) {
                    return Status.VENCEDOR_WO.getValue();
                }
                return Status.VENCEDOR.getValue();
            }
            if (wo) {
                return Status.PERDEDOR_WO.getValue();
            }
            return Status.PERDEDOR.getValue();
        }
        if (j1.getId() == idUsuario) {
            if (vezJ1)
                return Status.EH_VEZ.getValue();
            return Status.NAO_EH_VEZ.getValue();
        }

        if (j2.getId() == idUsuario) {
            if (!vezJ1)
                return Status.EH_VEZ.getValue();
            return Status.NAO_EH_VEZ.getValue();
        }
        return Status.ERRO.getValue();
    }

    public String obtemTabuleiro() {
        if (j1 == null || j2 == null)
            return "";
        return tabuleiro.toString();
    }

    public int movePeca(int idUsuario, int linha, int coluna, int direcao) {
        if (j1 == null || j2 == null)
            return -2;
        if ((j1.getId() == idUsuario && !vezJ1) || (j2.getId() == idUsuario && vezJ1)) {
            return -4;
        }
        if (passouUmMinuto())
            return 2;
        Jogador corrente = j1.getId() == idUsuario ? j1 : j2;
        int valor_retorno = tabuleiro.movePeca(corrente.getCor(), linha, coluna, direcao);
        checkSeTerminou();
        if (valor_retorno == 1) {
            vezJ1 = !vezJ1;
            timer = System.currentTimeMillis();
        }
        return valor_retorno;
    }

    private void checkSeTerminou() {
        Peca rei = tabuleiro.getPecaMeio();
        if (rei != null && rei.getTipo() == Tipo.REI) {
            if (rei.getCor() == Cor.CLARO) {
                vencedor = j1;
            } else {
                vencedor = j2;
            }
            encerrada = true;
        }
    }
}
