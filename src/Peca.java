public class Peca {

    private int x;
    private int y;
    private Tipo tipo;
    private Cor cor;

    public Peca(int x, int y, Tipo tipo, Cor cor) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.cor = cor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Cor getCor() {
        return cor;
    }
}
