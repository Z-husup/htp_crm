package com.example.htp_crm.service.interfaces;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;

import java.util.List;

public interface ApplicationServiceInterface {

    List<Application> getAllApplications();

    List<Application> getApplicationsByStatus(ApplicationStatus status);

    List<Application> getApplicationsByType(ApplicationType type);

    Application getApplicationById(Long applicationId);
    Application createApplication(ApplicationDto applicationDto);

    Application createOsooApplication(ApplicationDto applicationdto);

    Application createIpApplication(ApplicationDto applicationdto);

    Application updateApplication(Long applicationId, Application updatedApplication);
    void deleteApplication(Long applicationId);

}
