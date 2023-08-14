package com.example.htp_crm.controller;

import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public ResponseEntity<String> getAllApplications() {

        applicationService.getAllApplications();

        return ResponseEntity.ok("Task received successfully!");
    }

    @GetMapping("/applications/status")
    public ResponseEntity<String> getApplicationsByStatus(@RequestBody ApplicationStatus applicationStatus) {

        applicationService.getApplicationsByStatus(applicationStatus);

        return ResponseEntity.ok("Task received successfully!");
    }

    @GetMapping("examination")
    public ResponseEntity<Application> getApplication(@RequestParam Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("examination/approve")
    public ResponseEntity<Application> approveApplication(@RequestParam Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("examination/deny")
    public ResponseEntity<Application> denyApplication(@RequestParam Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId);

        if (application != null) {
            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
