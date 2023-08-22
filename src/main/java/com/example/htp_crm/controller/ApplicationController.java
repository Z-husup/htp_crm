package com.example.htp_crm.controller;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.repository.UploadedFileRepository;
import com.example.htp_crm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @RequestMapping(path = "/submit",method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createApplication(
            @RequestParam("articlesOfAccociation") MultipartFile articlesOfAccociation,
            @RequestParam("passportFounders") MultipartFile passportFounders,
            @RequestParam("passportCEO") MultipartFile passportCEO,
            @RequestParam("decisionAppointingCeo") MultipartFile decisionAppointingCeo,
            @RequestParam("balanceSheet") MultipartFile balanceSheet,
            @ModelAttribute ApplicationDto applicationDto) {

        applicationDto.setArticlesOfAccociation(articlesOfAccociation);
        applicationDto.setPassportFounders(passportFounders);
        applicationDto.setPassportCEO(passportCEO);
        applicationDto.setDecisionAppointingCeo(decisionAppointingCeo);
        applicationDto.setBalanceSheet(balanceSheet);

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
