import java.util.ArrayList;
import java.util.List;

public class Gerenciador {

    private static final int nro_partidas = 500;
    private List<Partida> partidas;

    public Gerenciador() {
        partidas = new ArrayList<>(nro_partidas);
        for (int i = 0; i < nro_partidas; i++) {
            partidas.add(new Partida());
        }
    }
}
