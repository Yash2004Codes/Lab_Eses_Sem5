import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;


class Server{

  int sid;
  int rid;

// constructor
public Server(int sid , int rid){

this.sid = sid;
this.rid = rid;

}

// method printing info

public void printInfo(){

System.out.println("server with id = "+sid + "assigned req with id = "+rid);

}

}

public class LoadBalancer {
  
 

  public static void main(String[] args) throws RemoteException{

  int index  = 0;
  int server_count = 3;
  int req_count = 10;

  for(int i=1;i<=req_count;i++){

    // distribure req to each sever
    int server_index = index % server_count;
    
   Server server = new Server(server_index,i);
   server.printInfo();
    
   index++;

}


}
}
