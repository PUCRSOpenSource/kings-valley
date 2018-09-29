import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoInterface extends Remote {

    int registraJogador(String nomeJogador) throws RemoteException;

    int encerraPartida(int idUsuario) throws RemoteException;

    int teamPartida(int idUsuario) throws RemoteException;

    String obtemOponente(int idUsuario) throws RemoteException;

    int ehMinhaVez(int idUsuario) throws RemoteException;

    String obtemTabuleiro(int idUsuario) throws RemoteException;

    int movePeca(int idUsuario, int linha, int coluna, int direcao) throws RemoteException;

}
