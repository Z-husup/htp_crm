package com.example.htp_crm.controller;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.repository.UploadedFileRepository;
import com.example.htp_crm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;


    @PostMapping("/submit")
    public ResponseEntity<String> createApplication(@RequestBody ApplicationDto applicationDto) {

        ApplicationType applicationType = ApplicationType.valueOf(applicationDto.getApplicationType());

        switch (applicationType) {
            case OSOO -> applicationService.createOsooApplication(applicationDto);
            case IP -> applicationService.createIpApplication(applicationDto);
            default -> {
            }
        }
        return ResponseEntity.ok("Task received successfully!");
    }
}
