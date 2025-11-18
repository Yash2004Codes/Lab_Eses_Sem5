import java.rmi.*;

public class Client {
    public static void main(String[] args) throws Exception {
        MyService service = (MyService) Naming.lookup("rmi://localhost/hello");
        System.out.println(service.sayHello("Yash"));
    }
}
