import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private Peca[][] tabuleiro;
    private List<Peca> pecas;

    public Tabuleiro() {
        tabuleiro = new Peca[5][5];
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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[j][i] != null) {
                    if (tabuleiro[j][i].getCor() == Cor.CLARO) {
                        if (tabuleiro[j][i].getTipo() == Tipo.SOLDADO) {
                            sb.append("c ");
                        } else {
                            sb.append("C ");
                        }
                    } else {
                        if (tabuleiro[j][i].getTipo() == Tipo.SOLDADO) {
                            sb.append("e ");
                        } else {
                            sb.append("E ");
                        }
                    }
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }


        return sb.toString();
    }

    public static void main(String args[]) {
        Tabuleiro t = new Tabuleiro();
        System.out.println(t.toString());
    }
}
