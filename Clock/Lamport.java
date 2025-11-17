import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

class Process {

    int id;
    ArrayList<Integer> vc;

    public Process(int id, int total) {
        this.id = id;
        vc = new ArrayList<>();
        for (int i = 0; i < total; i++) vc.add(0);
    }

    // internal or send event
    public ArrayList<Integer> update_onSend() {
        vc.set(id, vc.get(id) + 1);   // increment own clock
        return new ArrayList<>(vc);   // return copy to send
    }

    // receive message
    public void update_onReceive(ArrayList<Integer> msg) {
        for (int i = 0; i < vc.size(); i++) {
            vc.set(i, Math.max(vc.get(i), msg.get(i)));
        }
        vc.set(id, vc.get(id) + 1);   // increment after merge
    }

    public void printClock(String event) {
        System.out.println("P" + id + " " + event + " → " + vc);
    }
}

public class Lamport {

    public static void main(String[] args) {
        Process p0 = new Process(0, 3);
        Process p1 = new Process(1, 3);
        Process p2 = new Process(2, 3);

        // Simulation of events

        // Internal event at P0
        p0.update_onSend();
        p0.printClock("internal");

        // P0 → P1 message
        ArrayList<Integer> m1 = p0.update_onSend();
        p0.printClock("send");
        p1.update_onReceive(m1);
        p1.printClock("receive");

        // P1 → P2 message
        ArrayList<Integer> m2 = p1.update_onSend();
        p1.printClock("send");
        p2.update_onReceive(m2);
        p2.printClock("receive");

        // Internal event at P2
        p2.update_onSend();
        p2.printClock("internal");
    }
}
