package storage_application.demo.Service;

import org.springframework.stereotype.Service;
import storage_application.demo.Mapper.CredentialMapper;
import storage_application.demo.Model.Credential;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential, Principal principal) {
        int userId = userService.getUser(principal.getName()).getUserid();
        credential.setUserId(userId);
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setEncryption_key(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.insert(credential);
    }

    public List<Credential> getCredentials(Principal principal) {
        int userId = userService.getUser(principal.getName()).getUserid();
        List<Credential> credentials = credentialMapper.getCredentialsByUserId(userId);
        credentials.forEach(credential -> credential.setPassword("Encrypted"));
        return credentials;
    }

    public int updateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setEncryption_key(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.update(credential);
    }

    public int deleteCredential(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public Credential getCredential(Integer credentialId){
        Credential credential = credentialMapper.getCredential(credentialId);
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getEncryption_key());
        credential.setPassword(decryptedPassword);
        return credential;
    }
}