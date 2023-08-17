package com.example.htp_crm.controller;

import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.UploadedFile;
import com.example.htp_crm.model.User;
import com.example.htp_crm.repository.UploadedFileRepository;
import com.example.htp_crm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @GetMapping
    public ResponseEntity<String> getAllApplications() {

        applicationService.getAllApplications();

        return ResponseEntity.ok("Task received successfully!");
    }

    @GetMapping("/examination")
    public ResponseEntity<Application> getApplication(@RequestParam Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("examination/{applicationId}/approve")
    public ResponseEntity<Application> approveApplication(@PathVariable Long applicationId, @RequestBody User expert) {

        Application application = applicationService.getApplicationById(applicationId);
        applicationService.approveApplication(application, expert);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("examination/{applicationId}/deny")
    public ResponseEntity<Application> denyApplication(@PathVariable Long applicationId, @RequestBody User expert) {

        Application application = applicationService.getApplicationById(applicationId);
        applicationService.denyApplication(application, expert);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("examination/{applicationId}/postpone")
    public ResponseEntity<Application> postponeApplication(@PathVariable Long applicationId, @RequestBody User expert) {

        Application application = applicationService.getApplicationById(applicationId);
        applicationService.postponeApplication(application, expert);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("examination/file/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<UploadedFile> fileOptional = uploadedFileRepository.findById(id);

        if (fileOptional.isPresent()) {
            UploadedFile uploadedFile = fileOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(uploadedFile.getContentType()));
            headers.setContentDispositionFormData("attachment", uploadedFile.getFileName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(uploadedFile.getData());
        } else {
            // File not found
            return ResponseEntity.notFound().build();
        }
    }
}
