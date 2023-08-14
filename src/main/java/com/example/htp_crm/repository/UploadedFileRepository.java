package com.example.htp_crm.repository;

import com.example.htp_crm.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    UploadedFile findByFileName(String fileName);
}