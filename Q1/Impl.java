import java.rmi.*;
import java.util.*;
import java.io.*;
import java.rmi.server.*;

public class Impl extends UnicastRemoteObject implements myInterface {
  
  
 public Impl() throws RemoteException{
 super();
 }

  public String executeTask(String task) throws RemoteException{

    return "Task success";
  }
}
