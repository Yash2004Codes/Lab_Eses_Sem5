import java.util.*;
import java.io.*;
import java.rmi.*;

public interface myInterface extends Remote {
  
  public String executeTask(String task) throws RemoteException;

  // public return_type method(params) throws RemoteException;

}



/*
 * 
 
 public interface class_name extends Remote{
 
 
 public return_type method(params) throws RemoteException;
 
 }
 * 
 */