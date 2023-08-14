package com.example.htp_crm.service;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.repository.ApplicationRepository;
import com.example.htp_crm.service.interfaces.ApplicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ApplicationService implements ApplicationServiceInterface {

    @Autowired
    private final ApplicationService applicationService;
    @Autowired
    private final FileService fileService;
    @Autowired
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationService applicationService, FileService fileService, ApplicationRepository applicationRepository) {
        this.applicationService = applicationService;
        this.fileService = fileService;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByStatus(ApplicationStatus status) {

        return applicationRepository.findApplicationsByApplicationStatus(status);
    }

    @Override
    public List<Application> getApplicationsByType(ApplicationType type) {

        return applicationRepository.findApplicationsByApplicationType(type);
    }

    @Override
    public Application getApplicationById(Long applicationId) {

        return applicationRepository.findApplicationById(applicationId);
    }

    @Override
    public Application createApplication(ApplicationDto applicationDto) {
        return null;
    }

    @Override
    public Application createOsooApplication(ApplicationDto applicationdto) {

        Application application = new Application();

        application.setApplicationStatus(ApplicationStatus.IN_PROGRESS);
        application.setApplicationType(ApplicationType.OSOO);
        application.setCompanyNameRus(applicationdto.getCompanyNameRus());
        application.setCompanyNameEng(applicationdto.getCompanyNameEng());
        application.setRegistrationDate(applicationdto.getRegistrationDate());
        application.setRegistrationNumber(applicationdto.getRegistrationNumber());
        application.setResidentName(applicationdto.getResidentName());
        application.setResidentNameShort(applicationdto.getResidentNameShort());
        application.setResidentJobPosition(applicationdto.getResidentJobPosition());




        fileService.uploadFile("ArticlesOfAccociation", applicationdto.getArticlesOfAccociation());
        fileService.uploadFile("PassportCEO",applicationdto.getPassportCEO() );
        fileService.uploadFile("PassportFounders",applicationdto.getPassportFounders() );
        fileService.uploadFile("DecisionAppointingCeo",applicationdto.getDecisionAppointingCeo() );
        fileService.uploadFile("BalanceSheet",applicationdto.getBalanceSheet() );

        return null;
    }

    @Override
    public Application createIpApplication(ApplicationDto applicationdto) {

        Application application = new Application();

        application.setApplicationStatus(ApplicationStatus.IN_PROGRESS);
        application.setApplicationType(ApplicationType.IP);
        application.setCompanyNameRus(applicationdto.getCompanyNameRus());
        application.setCompanyNameEng(applicationdto.getCompanyNameEng());
        application.setRegistrationDate(applicationdto.getRegistrationDate());
        application.setRegistrationNumber(applicationdto.getRegistrationNumber());
        application.setResidentName(applicationdto.getResidentName());
        application.setResidentNameShort(applicationdto.getResidentNameShort());
        application.setResidentJobPosition(applicationdto.getResidentJobPosition());


        fileService.uploadFile("ArticlesOfAccociation",applicationdto.getArticlesOfAccociation() );
        fileService.uploadFile("PassportCEO",applicationdto.getPassportCEO() );
        fileService.uploadFile("PassportFounders",applicationdto.getPassportFounders() );
        fileService.uploadFile("BalanceSheet",applicationdto.getBalanceSheet() );

        return null;
    }


    @Override
    public Application updateApplication(Long applicationId, Application updatedApplication) {
        return null;
    }

    @Override
    public void deleteApplication(Long applicationId) {

    }
}
