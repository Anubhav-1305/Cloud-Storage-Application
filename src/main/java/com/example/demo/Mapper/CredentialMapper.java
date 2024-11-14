package com.example.demo.Mapper;

import com.example.demo.Model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Delete("DELETE FROM CREDENTIALS WHERE id = #{id} AND userid = #{userId}")
    void delete(Credential credential);

    @Select("SELECT id, url, password, username, userid FROM CREDENTIALS WHERE userid = #{UID} ORDER BY id DESC")
    List<Credential> allFrom(String UID);

    @Select("SELECT id, key, url, password, username, userid FROM CREDENTIALS WHERE id = #{id} AND userid = #{userId}")
    Credential find(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE id =#{id}")
    void update(Credential credential);

    @Insert("INSERT INTO CREDENTIALS (id, url, key, username, password, userid) VALUES(#{id}, #{url}, #{key}, #{username}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Credential credential);

}