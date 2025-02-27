package storage_application.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import storage_application.demo.Mapper.FileMapper;
import storage_application.demo.Model.File;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public int uploadFile(MultipartFile file, Principal principal) throws IOException {
        int userId = userService.getUser(principal.getName()).getUserid();
        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setContentType(file.getContentType());
        newFile.setFileSize(String.valueOf(file.getSize()));
        newFile.setUserId(userId);
        newFile.setFileData(file.getBytes());
        return fileMapper.insert(newFile);
    }

    public List<File> getFiles(Principal principal) {
        int userId = userService.getUser(principal.getName()).getUserid();
        return fileMapper.getFilesByUserId(userId);
    }

    public int deleteFile(Integer fileId) {
        return fileMapper.delete(fileId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }
}
