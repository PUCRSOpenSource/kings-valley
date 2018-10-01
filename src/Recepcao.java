import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Recepcao extends UnicastRemoteObject implements JogoInterface {

    private List<Partida> partidas;
    private List<Jogador> jogadores;
    private int contadorIds;

    protected Recepcao() throws RemoteException {
        jogadores = new LinkedList<>();
        partidas = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            partidas.add(new Partida());
        }
        contadorIds = 45;
    }

    @Override
    public synchronized int registraJogador(String nomeJogador) throws RemoteException {
        for (Jogador j : jogadores) {
            if (j.getNome().equals(nomeJogador))
                return -1;
        }
        Jogador jogador = new Jogador(contadorIds, nomeJogador);

        for (Partida p : partidas) {
            if (p.getJ1() == null) {
                jogador.setCor(Cor.CLARO);
                p.setJ1(jogador);
                p.setTimer(System.currentTimeMillis());
                contadorIds++;
                jogadores.add(jogador);
                return jogador.getId();
            }
            if (p.getJ2() == null) {
                jogador.setCor(Cor.ESCURO);
                p.setJ2(jogador);
                p.setTimer(System.currentTimeMillis());
                contadorIds++;
                jogadores.add(jogador);
                return jogador.getId();
            }

        }
        return -2;
    }

    @Override
    public synchronized int encerraPartida(int idUsuario) throws RemoteException {
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
        for (Partida p : partidas) {
            if (p.getJ1() != null && p.getJ1().getId() == idUsuario) {
                if (p.getJ2() != null)
                    return 1;
                if (p.passouUmMinuto()){
                    return -2;
                }
                return 0;
            }
            if (p.getJ2() != null && p.getJ2().getId() == idUsuario) {
                if (p.getJ1() != null)
                    return 2;
                if (p.passouUmMinuto()){
                    return -2;
                }
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
        for (Partida p : partidas) {
            if ((p.getJ1() != null && p.getJ1().getId() == idUsuario) || (p.getJ2() != null && p.getJ2().getId() == idUsuario)) {
                return p.obtemTabuleiro();
            }
        }
        return "";
    }

    @Override
    public synchronized int movePeca(int idUsuario, int linha, int coluna, int direcao) throws RemoteException {
        for (Partida p : partidas) {
            if ((p.getJ1() != null && p.getJ1().getId() == idUsuario) || (p.getJ2() != null && p.getJ2().getId() == idUsuario)) {
                return p.movePeca(idUsuario, linha, coluna, direcao);

            }
        }
        return -1;

    }

    private void removeJogador(int idUsuario) {
        for (Jogador j : jogadores) {
            if (j.getId() == idUsuario)
                jogadores.remove(j);
        }
    }

}