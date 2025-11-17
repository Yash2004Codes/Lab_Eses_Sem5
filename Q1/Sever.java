import java.rmi.registry.*;
import java.rmi.server.*;
import java.rmi.*;



public class Sever {
  

  public static void main(String[] args) throws RemoteException{

 try{
 
  Registry reg  = LocateRegistry.createRegistry(1098);
  reg.rebind("task",new Impl());
  System.out.println("server is running");


 }
 catch(Exception e){

 }



}

}
