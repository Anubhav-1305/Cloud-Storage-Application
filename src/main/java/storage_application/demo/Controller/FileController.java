package storage_application.demo.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import storage_application.demo.Service.FileService;

import java.io.IOException;
import java.security.Principal;

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, Model model) {
        try {
            fileService.uploadFile(file, principal);
            model.addAttribute("success", true);
            return "result";
        } catch (IOException e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            return "result";
        }
    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        try {
            fileService.deleteFile(fileId);
            model.addAttribute("success", true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Error deleting file: " + e.getMessage());
            return "result";
        }
    }

    @GetMapping("/file/view/{fileId}")
    public String viewFile(@PathVariable Integer fileId, Model model) {
        model.addAttribute("file", fileService.getFile(fileId));
        return "file-view"; // Create file-view.html
    }

    @GetMapping("/files/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId) {
        storage_application.demo.Model.File file = fileService.getFile(fileId);
        if (file == null || file.getFileData() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDispositionFormData("attachment", file.getFileName());

        return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
    }
}