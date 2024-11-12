package com.example.datnsum24sd01.fileupload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUpload {
    //upload ảnh sp
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile){
        Path uploadPath = Paths.get(uploadDir);

        try {
            InputStream inputStream = multipartFile.getInputStream();
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            System.out.println(ex);

        }
    }

}
