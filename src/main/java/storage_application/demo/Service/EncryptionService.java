package storage_application.demo.Service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class EncryptionService {

    public String encryptValue(String data, String key) {
        byte[] encryptedValue = null;

        try {
            Key secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            System.err.println("Error encrypting value: " + e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decryptValue(String data, String key) {
        byte[] decryptedValue = null;

        try {
            Key secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (Exception e) {
            System.err.println("Error decrypting value: " + e.getMessage());
        }

        return new String(decryptedValue);
    }
}