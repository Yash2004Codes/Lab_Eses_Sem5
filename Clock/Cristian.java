import java.util.*;

class TimeServer {
    int serverTime;

    TimeServer(int t) {
        this.serverTime = t;
    }

    // server returns its current time
    int getTime() {
        return serverTime;
    }
}

public class Cristian {
    public static void main(String[] args) throws Exception {

        TimeServer server = new TimeServer(200);  // server accurate time

        int T0 = 100;                // time when client sends request
        Thread.sleep(50);            // simulate network delay

        int Ts = server.getTime();   // server time returned

        Thread.sleep(50);            // simulate response delay
        int T1 = 150;                // time when client receives reply

        int networkDelay = (T1 - T0) / 2;

        int adjustedTime = Ts + networkDelay;

        System.out.println("T0 (send time): " + T0);
        System.out.println("T1 (receive time): " + T1);
        System.out.println("Server time Ts : " + Ts);
        System.out.println("Estimated delay: " + networkDelay);
        System.out.println("Adjusted client time: " + adjustedTime);
    }

    /*
     * 
     *  simple one client and one time server 
     * 
     * 
     */
}
