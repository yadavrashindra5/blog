package com.example.services.impl;

import com.example.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
//        path /home/rashi/Desktop/Program/MasterSpringBoot/blog/images/author/
//        here path is local storage but if you want ot store it into server than you have to provide the server path
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = fileName + extension;
        String fullPathWithFileName = path + fileNameWithExtension;
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }
        else{
            throw new RuntimeException("File not found");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
