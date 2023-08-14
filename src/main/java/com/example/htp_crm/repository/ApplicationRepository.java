package com.example.htp_crm.repository;

import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationsByApplicationStatus(ApplicationStatus status);
    List<Application> findApplicationsByApplicationType(ApplicationType type);
    Application findApplicationById(Long applicationId);
}