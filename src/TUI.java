import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
        jogar();
    }

    private void jogar() throws RemoteException, InterruptedException {
        while (true) {
            int status = esperaMeuTurno();
            switch (status) {
                case 3:
                    System.out.println("Voce PERDEU,loser");
                    jogo.encerraPartida(idUsuario);
                    return;
                case 2:
                    System.out.println("Voce venceu! \\o>");
                    return;
                case 4:
                    System.out.println("Empatou");
                    jogo.encerraPartida(idUsuario);
                    return;
                case 5:
                    System.out.println("Voce venceu por WO, sortudo");
                    return;
                case 6:
                    System.out.println("Voce perdeu por WO");
                    jogo.encerraPartida(idUsuario);
                    return;
            }
            facaJogada();
        }
    }

    private void facaJogada() throws RemoteException {
        System.out.println("Faça a sua jogada:");
        System.out.println("Digite a linha, seguida da coluna e depois da direçao:");
        System.out.println("DIREÇAO: 0 - direita, 1 - diagonal direita-inferior, 2 - para baixo, 3 - diagonal esquerda-inferior");
        System.out.println("         4 - esquerda, 5 - diagonal esquerda-superior, 6 - para cima, 7 - diagonal direita-superior");
        printaTabuleiro();
        String input = scanner.nextLine();
        int linha = Integer.parseInt(String.valueOf(input.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(input.charAt(1)));
        int direcao = Integer.parseInt(String.valueOf(input.charAt(2)));
        int resposta = jogo.movePeca(idUsuario, linha, coluna, direcao);
        if (resposta == 0 || resposta == -3) {
            System.out.println("Jogada invalida :( ");
        }
        printaTabuleiro();
    }

    private void printaTabuleiro() throws RemoteException {
        String tabuleiro = jogo.obtemTabuleiro(idUsuario);
        List<String> linhas = new ArrayList<>();
        int i = 0;
        while (i < tabuleiro.length()) {
            linhas.add(tabuleiro.substring(i, Math.min(i + 5, tabuleiro.length())));
            i += 5;
        }
        System.out.println("  01234");
        i = 0;
        for (String linha : linhas) {
            System.out.print(i + " ");
            System.out.println(linha);
            i++;
        }
    }

    private int esperaMeuTurno() throws InterruptedException, RemoteException {
        int status = 0;
        while (status != 1) {
            switch (status) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return status;
            }
            status = jogo.ehMinhaVez(idUsuario);
            Thread.sleep(1000);
        }
        return status;
    }

    private void achaJogo() throws RemoteException, InterruptedException {
        int status;
        System.out.print("Procurando uma partida");
        while (true) {
            status = jogo.temPartida(idUsuario);
            switch (status) {
                case -2:
                    System.out.println("\nTempo de espera esgotado!");
                    jogo.encerraPartida(idUsuario);
                    System.exit(1);
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
        if (idUsuario == -1) {
            System.out.println("Nome ja em uso.");
            System.exit(1);
        }
        System.out.println("LOG - ID do Usuario: " + idUsuario);
    }
}