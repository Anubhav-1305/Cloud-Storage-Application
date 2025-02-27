package storage_application.demo.Mapper;

import org.apache.ibatis.annotations.*;
import storage_application.demo.Model.Credential;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, encryption_key, password, userId) VALUES(#{url}, #{username}, #{encryption_key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, encryption_key = #{encryption_key}, password = #{password} WHERE credentialId = #{credentialId}")
    int update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int delete(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredential(Integer credentialId);
}