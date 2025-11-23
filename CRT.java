import java.util.*;
import java.math.*;


public class CRT {
    // Function to compute x using CRT
    static int crt(int[] rem, int[] mod) {
        int prod = 1;
        for(int m : mod) prod *= m;

        int result = 0;
        for(int i = 0; i < rem.length; i++) {
            int pp = prod / mod[i];
            result += rem[i] * pp * modInverse(pp, mod[i]);
        }
        return result % prod;
    }

    // Compute modular inverse using Extended Euclidean Algorithm
    static int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;
        if(m == 1) return 0;

        while(a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if(x1 < 0) x1 += m0;
        return x1;
    }

    public static void main(String[] args) {
        int[] rem = {2, 3, 2};
        int[] mod = {3, 5, 7};
        System.out.println("Solution x = " + crt(rem, mod)); // Output: 23
    }
}
