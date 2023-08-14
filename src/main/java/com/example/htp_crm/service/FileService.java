package com.example.htp_crm.service;

import com.example.htp_crm.model.UploadedFile;
import com.example.htp_crm.repository.UploadedFileRepository;
import com.example.htp_crm.service.interfaces.FileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService implements FileServiceInterface {

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Override
    public String uploadFile(String name, MultipartFile file) {

        try {
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setContentType(file.getContentType());
            uploadedFile.setData(file.getBytes());

            // Save the uploadedFile entity to the database using your repository
            uploadedFileRepository.save(uploadedFile);

            return  "File uploaded successfully";
        } catch (IOException e) {
            return  "File upload failed";
        }
    }

    @Override
    public ResponseEntity<Resource> getFile(UploadedFile uploadedFile) {

        if (uploadedFile != null) {
            // Convert the byte array data to a Resource object
            ByteArrayResource resource = new ByteArrayResource(uploadedFile.getData());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + uploadedFile.getFileName())
                    .contentType(MediaType.parseMediaType(uploadedFile.getContentType()))
                    .body(resource);
        } else {
            // File not found
            return ResponseEntity.notFound().build();
        }
    }
}
