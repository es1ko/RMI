import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a service which will be accessible remotely
 */


public interface Service extends Remote {
    Integer getMessage() throws RemoteException;
    void sendMessage(int num) throws RemoteException;
    void storeCalculatedMessage(int num) throws RemoteException;

}