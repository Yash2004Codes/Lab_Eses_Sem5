import java.util.*;

class Process {
    int id;
    boolean alive;

    Process(int id, boolean alive) {
        this.id = id;
        this.alive = alive;
    }
}

public class RingElection {

    public static void main(String[] args) {

        // Create processes in a ring (IDs MUST be unique)
        Process[] ring = {
            new Process(1, true),
            new Process(2, true),
            new Process(3, true),
            new Process(4, true),
            new Process(5, true)   // highest ID coordinator
        };

        // Simulate failure of coordinator (P5)
        ring[4].alive = false;
        System.out.println("P5 (Coordinator) FAILED.\n");

        int starter = 2;  // P3 detects failure (index 2)
        System.out.println("P" + ring[starter].id + " starts ELECTION...");

        ArrayList<Integer> electionList = new ArrayList<>();

        int n = ring.length;
        int i = starter;

        // circulate around ring
        do {
            if (ring[i].alive) {
                electionList.add(ring[i].id);
            }
            i = (i + 1) % n;
        } while (i != starter);

        // find new coordinator (highest ID)
        int newCoord = Collections.max(electionList);

        System.out.println("\nElection List: " + electionList);
        System.out.println("NEW COORDINATOR = P" + newCoord + "\n");

        // Send coordinator message around ring
        System.out.println("Sending COORDINATOR message around ring:");
        i = starter;
        do {
            System.out.println("Process P" + ring[i].id + " sets coordinator = P" + newCoord);
            i = (i + 1) % n;
        } while (i != starter);
    }
}
