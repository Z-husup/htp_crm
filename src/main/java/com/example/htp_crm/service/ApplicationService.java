package com.example.htp_crm.service;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.User;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.repository.ApplicationRepository;
import com.example.htp_crm.service.interfaces.ApplicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements ApplicationServiceInterface {

    @Autowired
    private final FileService fileService;
    @Autowired
    private final ApplicationRepository applicationRepository;

    public ApplicationService( FileService fileService, ApplicationRepository applicationRepository) {
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
        //Bank
        application.setBankId(applicationdto.getBankId());
        application.setBankAccountNumber(applicationdto.getBankAccountNumber());
        application.setBankName(applicationdto.getBankName());
        application.setBankLocation(applicationdto.getBankLocation());
        // Business
        application.setBusinessDescription(applicationdto.getBusinessDescription());
        application.setMarketDescription(applicationdto.getMarketDescription());
        application.setBusinessCharacteristics(applicationdto.getBusinessCharacteristics());
        // Contacts
        application.setCompanyEmail(applicationdto.getCompanyEmail());
        application.setCompanyNumber(applicationdto.getCompanyNumber());
        application.setCompanyWebsite(applicationdto.getCompanyWebsite());
        application.setDirectorFullName(applicationdto.getDirectorFullName());
        application.setDirectorNumber(applicationdto.getDirectorNumber());
        application.setDirectorEmail(applicationdto.getDirectorEmail());
        application.setAccountantFullName(applicationdto.getAccountantFullName());
        application.setAccountantNumber(applicationdto.getAccountantNumber());
        application.setAccountantEmail(applicationdto.getAccountantEmail());


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
        //Bank
        application.setBankId(applicationdto.getBankId());
        application.setBankAccountNumber(applicationdto.getBankAccountNumber());
        application.setBankName(applicationdto.getBankName());
        application.setBankLocation(applicationdto.getBankLocation());
        // Business
        application.setBusinessDescription(applicationdto.getBusinessDescription());
        application.setMarketDescription(applicationdto.getMarketDescription());
        application.setBusinessCharacteristics(applicationdto.getBusinessCharacteristics());
        // Contacts
        application.setCompanyEmail(applicationdto.getCompanyEmail());
        application.setCompanyNumber(applicationdto.getCompanyNumber());
        application.setCompanyWebsite(applicationdto.getCompanyWebsite());
        application.setDirectorFullName(applicationdto.getDirectorFullName());
        application.setDirectorNumber(applicationdto.getDirectorNumber());
        application.setDirectorEmail(applicationdto.getDirectorEmail());
        application.setAccountantFullName(applicationdto.getAccountantFullName());
        application.setAccountantNumber(applicationdto.getAccountantNumber());
        application.setAccountantEmail(applicationdto.getAccountantEmail());


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
    public void approveApplication(Application application, User expert) {
        application.getApproved().add(expert);
        application.setApprovalCount(application.getApprovalCount() + 1);
        if (application.getApprovalCount() >= 3) {
            application.setApplicationStatus(ApplicationStatus.APPROVED);
        }
        // Update database
        applicationRepository.save(application);
    }

    @Override
    public void denyApplication(Application application, User expert) {
        application.getDenied().add(expert);
        application.setDeniedCount(application.getDeniedCount() + 1);
        if (application.getDeniedCount() >= 3) {
            application.setApplicationStatus(ApplicationStatus.DENIED);
        }
        // Update database
        applicationRepository.save(application);
    }

    @Override
    public void postponeApplication(Application application, User expert) {
        application.getPostponed().add(expert);
        application.setPostonedCount(application.getPostonedCount() + 1);
        if (application.getPostonedCount() >= 3) {
            application.setApplicationStatus(ApplicationStatus.POSTPONED);
        }
        // Update database
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long applicationId) {

    }
}
