import java.rmi.RemoteException;
import java.util.Scanner;

public class TUI {
    private JogoInterface jogo;
    private Scanner scanner;
    private int idUsuario;
    private Cor cor;
    private String nomeOponente;

    public TUI(JogoInterface jogo) {
        this.jogo = jogo;
        scanner = new Scanner(System.in);
    }

    public void comecaJogo() throws RemoteException, InterruptedException {
        registraUsuario();
        achaJogo();
        //    jogar();
    }

    private void achaJogo() throws RemoteException, InterruptedException {
        int status;
        System.out.print("Procurando uma partida");
        while (true) {
            status = jogo.temPartida(idUsuario);
            switch (status) {
                case -2:
                    //TODO LIDAR COM O TEMPO (AGAIN)
                    break;
                case -1:
                    System.out.println("\nErro se comunicando com o servidor, tentando novamente");
                    break;
                case 0:
                    break;
                case 1:
                    cor = Cor.CLARO;
                    nomeOponente = jogo.obtemOponente(idUsuario);
                    System.out.println("\nVoce esta jogando contra: " + nomeOponente);
                    System.out.println("Voce esta jogando com as peças claras");
                    return;
                case 2:
                    cor = Cor.ESCURO;
                    nomeOponente = jogo.obtemOponente(idUsuario);
                    System.out.println("\nVoce esta jogando contra: " + nomeOponente);
                    System.out.println("Voce esta jogando com as peças escuras");
                    return;
            }
            System.out.print(".");
            Thread.sleep(1000);
        }
    }

    private void registraUsuario() throws RemoteException {
        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();
        idUsuario = jogo.registraJogador(nome);
        if(idUsuario == -1){
            System.out.println("Nome ja em uso.");
            System.exit(1);
        }
        System.out.println("LOG - ID do Usuario: " + idUsuario);
    }
}