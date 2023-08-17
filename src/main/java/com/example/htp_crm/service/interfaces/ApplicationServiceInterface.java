package com.example.htp_crm.service.interfaces;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.User;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.model.enums.Vote;

import java.util.List;

public interface ApplicationServiceInterface {

    List<Application> getAllApplications();

    List<Application> getApplicationsByStatus(ApplicationStatus status);

    List<Application> getApplicationsByType(ApplicationType type);

    Application getApplicationById(Long applicationId);

    Application createOsooApplication(ApplicationDto applicationdto);

    Application createIpApplication(ApplicationDto applicationdto);

    Application updateApplication(Long applicationId, Application updatedApplication);

    void approveApplication(Application application, User expert);

    void denyApplication(Application application, User expert);

    void postponeApplication(Application application, User expert);

    void addVote(Application application, User user, Vote vote);

    void deleteApplication(Long applicationId);

}
