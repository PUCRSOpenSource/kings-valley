import java.rmi.Naming;
import java.rmi.RemoteException;

public class Servidor {
    public static void main(String[] argv) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("RMI registry already running.");
        }
        try {
            Naming.rebind("Jogo", new Recepcao());
            System.out.println("Servidor do Jogo esta pronto.");
        } catch (Exception e) {
            System.out.println("Servidor do Jogo falhou:");
            e.printStackTrace();
        }
    }
}