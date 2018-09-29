public class Partida {

    private Tabuleiro tabuleiro;
    private Jogador j1;
    private Jogador j2;

    public Partida() {
        tabuleiro = new Tabuleiro();
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
}
