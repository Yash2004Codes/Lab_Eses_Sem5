package Lab_Eses_Sem5.Q1;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class HybridWithSignature {
    public static void main(String[] args) throws Exception {

        // ---------- Sender generates RSA key pair for signing ----------
        KeyPair senderKP = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PublicKey senderPub = senderKP.getPublic();
        PrivateKey senderPriv = senderKP.getPrivate();

        // ---------- Receiver generates RSA key pair ----------
        KeyPair receiverKP = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PublicKey receiverPub = receiverKP.getPublic();
        PrivateKey receiverPriv = receiverKP.getPrivate();

        // ---------- Sender generates AES key ----------
        SecretKey aesKey = KeyGenerator.getInstance("AES").generateKey();
        System.out.println("Sender: Generated AES key = " + Base64.getEncoder().encodeToString(aesKey.getEncoded()));

        // ---------- Sender encrypts AES key with receiver's public key ----------
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE, receiverPub);
        String encAESKey = Base64.getEncoder().encodeToString(rsa.doFinal(aesKey.getEncoded()));
        System.out.println("Sender: Encrypted AES key sent = " + encAESKey);

        // ---------- Sender encrypts message with AES ----------
        String msg = "Hello Yash! AES+RSA+SIGN.";
        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.ENCRYPT_MODE, aesKey);
        String cipherText = Base64.getEncoder().encodeToString(aes.doFinal(msg.getBytes()));
        System.out.println("\nSender: Plaintext = " + msg);
        System.out.println("Sender: Ciphertext sent = " + cipherText);

        // ---------- Sender signs the message ----------
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(senderPriv);
        sign.update(msg.getBytes());
        String digitalSignature = Base64.getEncoder().encodeToString(sign.sign());
        System.out.println("Sender: Digital Signature = " + digitalSignature);

        // ---------- Receiver decrypts AES key ----------
        rsa.init(Cipher.DECRYPT_MODE, receiverPriv);
        SecretKey decAESKey = new SecretKeySpec(rsa.doFinal(Base64.getDecoder().decode(encAESKey)), "AES");
        System.out.println("\nReceiver: Decrypted AES key = " + Base64.getEncoder().encodeToString(decAESKey.getEncoded()));

        // ---------- Receiver decrypts message ----------
        aes.init(Cipher.DECRYPT_MODE, decAESKey);
        String decryptedMsg = new String(aes.doFinal(Base64.getDecoder().decode(cipherText)));
        System.out.println("Receiver: Decrypted Plaintext = " + decryptedMsg);

        // ---------- Receiver verifies signature ----------
        Signature verify = Signature.getInstance("SHA256withRSA");
        verify.initVerify(senderPub);
        verify.update(decryptedMsg.getBytes());
        boolean isVerified = verify.verify(Base64.getDecoder().decode(digitalSignature));
        System.out.println("Receiver: Signature Verified = " + isVerified);
    }
}
