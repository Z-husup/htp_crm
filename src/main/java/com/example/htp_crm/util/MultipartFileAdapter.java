package com.example.htp_crm.util;

import com.example.htp_crm.model.UploadedFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class MultipartFileAdapter implements MultipartFile {

    

    private final UploadedFile uploadedFile;

    public MultipartFileAdapter(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    @Override
    public String getName() {
        return uploadedFile.getFileName();
    }

    @Override
    public String getOriginalFilename() {
        return uploadedFile.getFileName();
    }

    @Override
    public String getContentType() {
        return uploadedFile.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return uploadedFile.getData() == null || uploadedFile.getData().length == 0;
    }

    @Override
    public long getSize() {
        return uploadedFile.getData() != null ? uploadedFile.getData().length : 0;
    }

    @Override
    public byte[] getBytes() {
        return uploadedFile.getData();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }

    // Other methods...

}