package storage_application.demo.Model;

public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String encryption_key;
    private String password;
    private Integer userId;

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryption_key() {
        return encryption_key;
    }

    public void setEncryption_key(String encryption_key) {
        this.encryption_key = encryption_key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}