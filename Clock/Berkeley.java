import java.util.*;

class Node {
    int time; // simple integer time

    Node(int t) {
        this.time = t;
    }
}

public class Berkeley {
    public static void main(String[] args) {

        // Create 4 nodes with different clock times
        Node master = new Node(100);   // master node time
        Node n1 = new Node(105);
        Node n2 = new Node(97);
        Node n3 = new Node(110);

        // Put slave times in list
        ArrayList<Node> slaves = new ArrayList<>();
        slaves.add(n1);
        slaves.add(n2);
        slaves.add(n3);

        System.out.println("Before Sync:");
        System.out.println("Master : " + master.time);
        System.out.println("N1     : " + n1.time);
        System.out.println("N2     : " + n2.time);
        System.out.println("N3     : " + n3.time);

        // ===== 1. Master polls slaves and collects times =====
        int sum = master.time;
        for (Node n : slaves) sum += n.time;

        // ===== 2. Calculate average time =====
        int avg = sum / (slaves.size() + 1);

        // ===== 3. Send corrections to slaves =====
        master.time = avg;
        for (Node n : slaves) n.time = avg;

        System.out.println("\nAfter Berkeley Sync:");
        System.out.println("Master : " + master.time);
        System.out.println("N1     : " + n1.time);
        System.out.println("N2     : " + n2.time);
        System.out.println("N3     : " + n3.time);

        /*
        
Before Sync:
Master : 100
N1     : 105
N2     : 97
N3     : 110

After Berkeley Sync:
Master : 103
N1     : 103
N2     : 103
N3     : 103

         * 
         * 
         */
    }
}
