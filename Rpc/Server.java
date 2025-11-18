import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class Server extends UnicastRemoteObject implements MyService {

    protected Server() throws RemoteException {}

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public static void main(String[] args) throws Exception {

        LocateRegistry.createRegistry(1099);  // start RMI registry
        Naming.rebind("hello", new Server()); // bind service
        System.out.println("Server running...");
    }
}
