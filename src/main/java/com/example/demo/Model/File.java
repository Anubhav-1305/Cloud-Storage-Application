package com.example.demo.Model;

public class File {

    private Integer id;
    private String name;
    private byte[] data;
    private long sizes;
    private Integer userId;
    private String contentType;

    public File() {}

    public File(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public File(String name, long size, String contentType, byte[] data, Integer userId) {
        this.name = name;
        this.data = data;
        this.sizes = size;
        this.userId = userId;
        this.contentType = contentType;
    }

    public long getSize() {
        return sizes;
    }

    public void setSize(long size) {
        this.sizes = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
