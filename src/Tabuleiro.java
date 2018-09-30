import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private static final int size = 5;
    private Peca[][] tabuleiro;
    private List<Peca> pecas;

    public Tabuleiro() {
        tabuleiro = new Peca[size][size];
        pecas = new ArrayList<>();
        Peca peca;
        peca = new Peca(0, 0, Tipo.SOLDADO, Cor.CLARO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(0, 1, Tipo.SOLDADO, Cor.CLARO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(0, 2, Tipo.REI, Cor.CLARO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(0, 3, Tipo.SOLDADO, Cor.CLARO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(0, 4, Tipo.SOLDADO, Cor.CLARO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);

        peca = new Peca(4, 0, Tipo.SOLDADO, Cor.ESCURO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(4, 1, Tipo.SOLDADO, Cor.ESCURO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(4, 2, Tipo.REI, Cor.ESCURO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(4, 3, Tipo.SOLDADO, Cor.ESCURO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
        peca = new Peca(4, 4, Tipo.SOLDADO, Cor.ESCURO);
        tabuleiro[peca.getY()][peca.getX()] = peca;
        pecas.add(peca);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].getCor() == Cor.CLARO) {
                        if (tabuleiro[i][j].getTipo() == Tipo.SOLDADO) {
                            sb.append("c");
                        } else {
                            sb.append("C");
                        }
                    } else {
                        if (tabuleiro[i][j].getTipo() == Tipo.SOLDADO) {
                            sb.append("e");
                        } else {
                            sb.append("E");
                        }
                    }
                } else {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        Tabuleiro t = new Tabuleiro();
        System.out.println(t.toString());
    }

    public int movePeca(Cor cor, int linha, int coluna, int direcao) {
        if(tabuleiro[linha][coluna].getCor() != cor){
            return -3;
        }
        return 0;
    }
}
