import java.rmi.registry.*;
import java.rmi.*;



public class Client{

  public static void main(String[] args) throws RemoteException{

    try{

      Registry reg = LocateRegistry.getRegistry("localhost",1098);
      myInterface obj = (myInterface) reg.lookup("task");

       System.out.println(obj.executeTask("yash"));
       System.out.println(obj.executeTask("yash is goat"));


    }
    catch(Exception e){


    }
}


}