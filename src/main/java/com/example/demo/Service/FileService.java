package com.example.demo.Service;

import com.example.demo.Mapper.FileMapper;
import com.example.demo.Model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper files;

    public FileService(FileMapper mapper) {
        files = mapper;
    }

    public File get(File file) {
        return files.get(file);
    }

    public List<File> allBy(String id) {
        return files.allFrom(id);
    }

    public void remove(File file) {
        files.delete(file);
    }

    public boolean exists(File file) {
        return files.find(file) != null;
    }

    public void store(File file) {
        files.insert(file);
    }

}