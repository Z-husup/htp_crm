package com.example.htp_crm.dto;

import com.example.htp_crm.model.Founder;
import com.example.htp_crm.model.UploadedFile;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ApplicationDto {

    private String applicationStatus;

    private String applicationType;

    private String companyNameEng;

    private String companyNameRus;

    private Date registrationDate;

    private String residentJobPosition;

    private String residentName;

    private String residentNameShort;

    private String registrationNumber;

    private String codeOKPO;


    //    BANK
    private String bankName;

    private String bankId;  //БИК

    private String bankAccountNumber;

    private String paymentNumber;

    private String bankLocation;

    private String stateTaxLocation;

    private String socialFundLocation;


    //    BUSINESS
    private String businessDescription;

    private String marketDescription;

    private String businessCharacteristics;


    //    CONTACT
    private String companyEmail;

    private String companyNumber;

    private String companyWebsite;

    private String directorFullName;

    private String directorNumber;

    private String directorEmail;

    private String accountantFullName;

    private String accountantNumber;

    private String accountantEmail;


    //    FILES
    private MultipartFile articlesOfAccociation;//Articles of Association(УСТАВ)

    private MultipartFile passportFounders;//Articles of Association(ПАСПОРТА УЧРЕДИТЕЛЕЙ)

    private MultipartFile passportCEO;//Articles of Association(ПАСПОРТ РУКОВОДИТЕЛЯ)

    private MultipartFile decisionAppointingCeo;//Articles of Association(РЕШЕНИЕ О НАЗНАЧЕНИИ РУКОВОДИТЕЛЯ)

    private MultipartFile balanceSheet;//Balance Sheet / Certificate of No Debts(БУХГАЛТЕРСКИЙ БАЛАНС)


    //    FOUNDERS
    private Set<Founder> founders = new HashSet<>();
}
