import java.rmi.*;

public interface MyService extends Remote {
    String sayHello(String name) throws RemoteException;
}
