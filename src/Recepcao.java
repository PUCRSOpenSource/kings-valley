import com.sun.xml.internal.bind.v2.TODO;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class Recepcao implements JogoInterface {

    private List<Partida> partidas;
    private List<Jogador> jogadores;
    private int contadorIds;

    public Recepcao() {

        jogadores = new LinkedList<>();
        partidas = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            partidas.add(new Partida());
        }
        contadorIds = 0;
    }

    @Override
    public int registraJogador(String nomeJogador) throws RemoteException {
        for (Jogador j : jogadores) {
            if (j.getNome().equals(nomeJogador))
                return -1;
        }
        Jogador jogador = new Jogador(contadorIds, nomeJogador);

        for (Partida p : partidas) {
            if (p.getJ1() == null) {
                p.setJ1(jogador);
                contadorIds++;
                return jogador.getId();
            }
            if (p.getJ2() == null) {
                p.setJ2(jogador);
                contadorIds++;
                return jogador.getId();
            }

        }
        return -2;
    }

    @Override
    public int encerraPartida(int idUsuario) throws RemoteException {
        for (Partida p : partidas) {
            if (p.getJ1() != null && p.getJ1().getId() == idUsuario) {
                if (p.getJ2() != null)
                    removeJogador(p.getJ2().getId());
                removeJogador(idUsuario);
                partidas.remove(p);
                return 0;
            }
            if (p.getJ2() != null && p.getJ2().getId() == idUsuario) {
                if (p.getJ1() != null)
                    removeJogador(p.getJ1().getId());
                removeJogador(idUsuario);
                partidas.remove(p);
                return 0;
            }
        }
        return -1;
    }

    @Override
    public int temPartida(int idUsuario) throws RemoteException {
        //TODO: LIDAR COM O TEMPO
        for (Partida p : partidas) {
            if (p.getJ1() != null && p.getJ1().getId() == idUsuario) {
                if (p.getJ2() != null)
                    return 1;
                return 0;
            }
            if (p.getJ2() != null && p.getJ2().getId() == idUsuario) {
                if (p.getJ1() != null)
                    return 2;
                return 0;
            }

        }

        return -1;
    }

    @Override
    public String obtemOponente(int idUsuario) throws RemoteException {
        for (Partida p : partidas) {
            if (p.getJ1() != null && p.getJ1().getId() == idUsuario) {
                if (p.getJ2() != null) {
                    return p.getJ2().getNome();
                }
                return "";
            }
            if (p.getJ2() != null && p.getJ2().getId() == idUsuario) {
                if (p.getJ1() != null) {
                    return p.getJ1().getNome();
                }
                return "";
            }
        }

        return "";
    }

    @Override
    public int ehMinhaVez(int idUsuario) throws RemoteException {
        for (Partida p : partidas) {
            if ((p.getJ1() != null && p.getJ1().getId() == idUsuario) || (p.getJ2() != null && p.getJ2().getId() == idUsuario)) {
                return p.getStatus(idUsuario);
            }
        }
        return -1;
    }

    @Override
    public String obtemTabuleiro(int idUsuario) throws RemoteException {
        return null;
    }

    @Override
    public int movePeca(int idUsuario, int linha, int coluna, int direcao) throws RemoteException {
        return 0;
    }

    private void removeJogador(int idUsuario) {
        for (Jogador j : jogadores) {
            if (j.getId() == idUsuario)
                jogadores.remove(j);
        }
    }

}

