import java.rmi.RemoteException;
import java.util.Scanner;

public class TUI {
    private JogoInterface jogo;
    private Scanner scanner;
    private int idUsuario;

    public TUI(JogoInterface jogo) {
        this.jogo = jogo;
        scanner = new Scanner(System.in);
    }

    public void comecaJogo() throws RemoteException, InterruptedException {
        registraUsuario();
        achaJogo();
        //    jogar();
    }

    private void achaJogo() {
    }

    private void registraUsuario() throws RemoteException {
        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();
        idUsuario = jogo.registraJogador(nome);
        System.out.println("LOG - ID do Usuario: " + idUsuario);
    }
}
