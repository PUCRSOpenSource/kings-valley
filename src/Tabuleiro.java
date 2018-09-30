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
        Direcao dir = Direcao.fromInteger(direcao);
        if (tabuleiro[linha][coluna] == null || tabuleiro[linha][coluna].getCor() != cor || dir == null) {
            return -3;
        }
        if (!direcaoValida(linha, coluna, dir))
            return 0;
        Peca peca = tabuleiro[linha][coluna];
        tabuleiro[linha][coluna] = null;
        do {
            int x = 0;
            int y = 0;
            switch (dir) {
                case DIREITA:
                    x = coluna + 1;
                    y = linha;
                    break;
                case DIAGONAL_DIREITA_INFERIOR:
                    x = coluna + 1;
                    y = linha + 1;
                    break;
                case BAIXO:
                    x = coluna;
                    y = linha + 1;
                    break;
                case DIAGONAL_ESQUERDA_INFERIOR:
                    x = coluna - 1;
                    y = linha + 1;
                    break;
                case ESQUERDA:
                    x = coluna - 1;
                    y = linha;
                    break;
                case DIAGONAL_ESQUERDA_SUPERIOR:
                    x = coluna - 1;
                    y = linha - 1;
                    break;
                case CIMA:
                    x = coluna;
                    y = linha - 1;
                    break;
                case DIAGONAL_DIREITA_SUPERIOR:
                    x = coluna + 1;
                    y = linha - 1;
                    break;
            }
            coluna = x;
            linha = y;
        } while (direcaoValida(linha, coluna, dir));
        tabuleiro[linha][coluna] = peca;
        peca.setX(coluna);
        peca.setY(linha);
        return 1;
    }

    private boolean direcaoValida(int linha, int coluna, Direcao direcao) {
        int x = 0;
        int y = 0;
        switch (direcao) {
            case DIREITA:
                x = coluna + 1;
                y = linha;
                break;
            case DIAGONAL_DIREITA_INFERIOR:
                x = coluna + 1;
                y = linha + 1;
                break;
            case BAIXO:
                x = coluna;
                y = linha + 1;
                break;
            case DIAGONAL_ESQUERDA_INFERIOR:
                x = coluna - 1;
                y = linha + 1;
                break;
            case ESQUERDA:
                x = coluna - 1;
                y = linha;
                break;
            case DIAGONAL_ESQUERDA_SUPERIOR:
                x = coluna - 1;
                y = linha - 1;
                break;
            case CIMA:
                x = coluna;
                y = linha - 1;
                break;
            case DIAGONAL_DIREITA_SUPERIOR:
                x = coluna + 1;
                y = linha - 1;
                break;
        }
        if (x < 0 || y < 0 || x >= size || y >= size || tabuleiro[y][x] != null)
            return false;
        return true;
    }

    public Peca getPecaMeio() {
        return tabuleiro[2][2];
    }
}