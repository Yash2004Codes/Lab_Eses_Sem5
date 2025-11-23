import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;


public class Rsa {

    public static void main(String[] args) throws Exception {

        // ---------- Sender generates RSA keys ----------
        KeyPair kp = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PublicKey pub = kp.getPublic();
        PrivateKey priv = kp.getPrivate();


        // ---------- Sender generates AES key ----------
        SecretKey aesKey = KeyGenerator.getInstance("AES").generateKey();
        System.out.println("Sender: Generated AES key (Base64) = " +
                Base64.getEncoder().encodeToString(aesKey.getEncoded()));

                
        // ---------- Sender encrypts AES key with RSA ----------
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE, pub);
        String encAESKey = Base64.getEncoder().encodeToString(rsa.doFinal(aesKey.getEncoded()));
        System.out.println("Sender: Encrypted AES key sent to Receiver = " + encAESKey);

        // ---------- Receiver decrypts AES key ----------
        rsa.init(Cipher.DECRYPT_MODE, priv);
        SecretKey decAESKey = new SecretKeySpec(rsa.doFinal(Base64.getDecoder().decode(encAESKey)), "AES");
        System.out.println("Receiver: Decrypted AES key = " +
                Base64.getEncoder().encodeToString(decAESKey.getEncoded()));

        // ---------- Sender encrypts message with AES ----------
        String msg = "Hello Yash! AES+RSA.";
        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.ENCRYPT_MODE, aesKey);
        String cipherText = Base64.getEncoder().encodeToString(aes.doFinal(msg.getBytes()));
        System.out.println("\nSender: Plaintext = " + msg);
        System.out.println("Sender: Ciphertext sent = " + cipherText);

        // ---------- Receiver decrypts message ----------
        aes.init(Cipher.DECRYPT_MODE, decAESKey);
        String decryptedMsg = new String(aes.doFinal(Base64.getDecoder().decode(cipherText)));
        System.out.println("Receiver: Decrypted Plaintext = " + decryptedMsg);
    }
}
