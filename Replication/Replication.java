import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;



class Nodes{

String name;
HashMap<String,String> data = new HashMap<>();


public Nodes(String name){
  
  this.name = name;
  
}

public void  set(String key , String value){


  data.put(key,value);
}





}
public class Replication {
 
  public static void main(String [] args){


     
Nodes node1 = new Nodes("node1");
Nodes node2 = new Nodes("node2");
Nodes node3 = new Nodes("node3");

node1.set("name","yash");
node2.set("name","yash");
node3.set("name","yash");
System.out.println("before change :\n");

System.out.println("node1 :-"+node1.data);
System.out.println("node2 :-"+node2.data);
System.out.println("node3 :-"+node3.data);

node1.set("name","yash harmai");

try{
  Thread.sleep(1000);
}
catch(InterruptedException e){
  
}

node2.set("name","yash harami");
node3.set("name","yash harami");



System.out.println("after  delay :\n");

System.out.println("node1 :-"+node1.data);
System.out.println("node2 :-"+node2.data);
System.out.println("node3 :-"+node3.data);


  }
}
