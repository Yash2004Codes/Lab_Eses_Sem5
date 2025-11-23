public class Practise {

  // ---------------- Modular Exponentiation ----------------
  static int modPow(int base, int exp, int mod) {
      int result = 1;
      base = base % mod;
      while (exp > 0) {
          if (exp % 2 == 1)
              result = (result * base) % mod;
          exp = exp / 2;
          base = (base * base) % mod;
      }
      return result;
  }

  // ---------------- RSA Functions ----------------
  static int rsaEncrypt(int m, int e, int n) {
      return modPow(m, e, n);
  }

  static int rsaDecrypt(int c, int d, int n) {
      return modPow(c, d, n);
  }

  // ---------------- Caesar Cipher ----------------
  static int caesarEncrypt(int msg, int key) {
      return (msg + key) % 26;
  }

  static int caesarDecrypt(int cipher, int key) {
      return (cipher - key + 26) % 26;
  }

  public static void main(String[] args) {

      // -------------------- SENDER KEYS --------------------
      int pS = 3, qS = 5;
      int nS = pS * qS;          // 15
      int phiS = (pS - 1) * (qS - 1); // 8
      int eS = 3;                // sender public key
      int dS = 3;                // sender private key (3*3=9 ≡1 mod 8)

      // -------------------- RECEIVER KEYS --------------------
      int pR = 5, qR = 7;
      int nR = pR * qR;          // 35
      int phiR = (pR - 1) * (qR - 1); // 24
      int eR = 5;                // receiver public key
      int dR = 29;               // receiver private key (5*29=145 ≡1 mod 24)

      // -------------------- SENDER SIDE --------------------
      int symKey = 3;  // symmetric key < nR
      System.out.println("Sender: Symmetric Key = " + symKey);

      // Encrypt symmetric key with receiver's public key
      int encKey = rsaEncrypt(symKey, eR, nR);
      System.out.println("Sender: Encrypted Key Sent = " + encKey);

      // Message to send (must be coprime with nS for signature)
      int msg = 2;
      System.out.println("Sender: Plain Message = " + msg);

      // Encrypt message with symmetric key (Caesar cipher)
      int cipherMsg = caesarEncrypt(msg, symKey);
      System.out.println("Sender: Cipher Text = " + cipherMsg);

      // Sign message using sender's private key
      int signature = rsaEncrypt(msg, dS, nS);
      System.out.println("Sender: Signature = " + signature);

      // -------------------- RECEIVER SIDE --------------------
      // Decrypt symmetric key using receiver's private key
      int decKey = rsaDecrypt(encKey, dR, nR);
      System.out.println("Receiver: Decrypted Symmetric Key = " + decKey);

      // Decrypt message
      int decryptedMsg = caesarDecrypt(cipherMsg, decKey);
      System.out.println("Receiver: Decrypted Message = " + decryptedMsg);

      // Verify signature using sender's public key
      int verifySig = rsaDecrypt(signature, eS, nS);
      System.out.println("Receiver: Signature Verifies To = " + verifySig);

      if (verifySig == msg)
          System.out.println("Signature Verified ✓");
      else
          System.out.println("Signature Failed ✗");
  }
}
