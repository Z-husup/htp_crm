package com.example.htp_crm.model;

import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Getter
@Setter
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ApplicationStatus applicationStatus;

    private ApplicationType applicationType;

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
    @OneToOne
    @JoinColumn(name = "articles_of_accociation_id")
    private UploadedFile articlesOfAccociation;//Articles of Association(УСТАВ)

    @OneToOne
    @JoinColumn(name = "passport_founders_id")
    private UploadedFile passportFounders;//Articles of Association(ПАСПОРТА УЧРЕДИТЕЛЕЙ)

    @OneToOne
    @JoinColumn(name = "passport_ceo_id")
    private UploadedFile passportCEO;//Articles of Association(ПАСПОРТ РУКОВОДИТЕЛЯ)

    @OneToOne
    @JoinColumn(name = "decision_appointing_ceo_id")
    private UploadedFile decisionAppointingCeo;//Articles of Association(РЕШЕНИЕ О НАЗНАЧЕНИИ РУКОВОДИТЕЛЯ)

    @OneToOne
    @JoinColumn(name = "balance_sheet_id")
    private UploadedFile balanceSheet;//Balance Sheet / Certificate of No Debts(БУХГАЛТЕРСКИЙ БАЛАНС)

    @OneToOne
    @JoinColumn(name = "pdf_report_id")
    private UploadedFile pdfReport;//Balance Sheet / Certificate of No Debts(БУХГАЛТЕРСКИЙ БАЛАНС)


    //    FOUNDERS
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private Set<Founder> founders = new HashSet<>();

}
