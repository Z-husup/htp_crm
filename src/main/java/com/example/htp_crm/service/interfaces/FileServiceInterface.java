package com.example.htp_crm.service.interfaces;

import com.example.htp_crm.model.UploadedFile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


public interface FileServiceInterface {

    String uploadFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file);

    ResponseEntity<Resource> getFile(UploadedFile uploadedFile);
}
