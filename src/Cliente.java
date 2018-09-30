import java.rmi.Naming;

public class Cliente {
    public static void main (String[] argv) {
        try {
            JogoInterface jogo = (JogoInterface) Naming.lookup ("//localhost/Jogo");
            new TUI(jogo).comecaJogo();
        } catch (Exception e) {
            System.out.println ("HelloClient failed:");
            e.printStackTrace();
        }
    }
}
