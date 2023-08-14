package com.example.htp_crm.controller;

import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")

public class AdminController {

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

}
