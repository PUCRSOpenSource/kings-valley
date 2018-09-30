public class Partida {

    private Tabuleiro tabuleiro;
    private Jogador j1;
    private Jogador j2;
    private boolean vezJ1;
    private boolean encerrada;
    private Jogador vencedor;

    public Partida() {
        tabuleiro = new Tabuleiro();
        vezJ1 = true;
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

    public int getStatus(int idUsuario) {
        //TODO LIDAR COM O TEMPO (WO)
        if (j1 == null || j2 == null)
            return Status.FALTA_JOGADOR.getValue();
        if (encerrada) {
            if (vencedor == null) {
                return Status.EMPATE.getValue();
            }
            if (vencedor.getId() == idUsuario) {
                return Status.VENCEDOR.getValue();
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
}
