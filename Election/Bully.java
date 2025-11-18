import java.util.*;

class Process {
    int id;
    boolean alive;

    Process(int id, boolean alive) {
        this.id = id;
        this.alive = alive;
    }
}

public class Bully {
    public static void main(String[] args) {

        // Create 5 processes (IDs must be unique)
        Process[] p = {
            new Process(1, true),
            new Process(2, true),
            new Process(3, true),
            new Process(4, true),
            new Process(5, true) // highest ID = coordinator
        };

        // Simulate failure of coordinator
        p[4].alive = false;  // P5 failed
        System.out.println("P5 (Coordinator) FAILED\n");

        // Assume P2 detects failure and starts election
        int starter = 2;
        System.out.println("P" + starter + " starts election...");

        // Election logic (simple)
        int newCoordinator = -1;

        for (Process pr : p) {
            if (pr.id > starter && pr.alive) {
                System.out.println("P" + pr.id + " replies OK");
                newCoordinator = pr.id;
            }
        }

        // Highest alive process will win
        for (int i = p.length-1; i >= 0; i--) {
            if (p[i].alive) {
                newCoordinator = p[i].id;
                break;
            }
        }

        System.out.println("\nNEW COORDINATOR = P" + newCoordinator);
    }
}
