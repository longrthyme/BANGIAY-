package com.example.datnsum24sd01.fileupload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;
//đường dẫn upload ảnh trả hàng
    public String storeFile(MultipartFile hinhAnh) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = hinhAnh.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(hinhAnh.getInputStream(), filePath);

        return fileName;
    }
}
