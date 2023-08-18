package com.example.htp_crm.model;

import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.util.MultipartFileAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private ApplicationStatus applicationStatus;

    @Enumerated
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articles_of_accociation_id", referencedColumnName = "id")
    private UploadedFile articlesOfAccociation;//Articles of Association(УСТАВ)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_founders_id", referencedColumnName = "id")
    private UploadedFile passportFounders;//Articles of Association(ПАСПОРТА УЧРЕДИТЕЛЕЙ)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_ceo_id", referencedColumnName = "id")
    private UploadedFile passportCEO;//Articles of Association(ПАСПОРТ РУКОВОДИТЕЛЯ)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "decision_appointing_ceo_id", referencedColumnName = "id")
    private UploadedFile decisionAppointingCeo;//Articles of Association(РЕШЕНИЕ О НАЗНАЧЕНИИ РУКОВОДИТЕЛЯ)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_sheet_id", referencedColumnName = "id")
    private UploadedFile balanceSheet;//Balance Sheet / Certificate of No Debts(БУХГАЛТЕРСКИЙ БАЛАНС)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdf_report_id", referencedColumnName = "id")
    private UploadedFile pdfReport;


    //    FOUNDERS
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private Set<Founder> founders = new HashSet<>();

    //    Examination
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private Set<UserApplicationVote> votes = new HashSet<>();


    public MultipartFile getArticlesOfAccociationMultipartFile() {
        return uploadedFileToMultipartFile(articlesOfAccociation);
    }

    public MultipartFile getPassportFoundersMultipartFile() {
        return uploadedFileToMultipartFile(passportFounders);
    }

    public MultipartFile getPassportCEOMultipartFile() {
        return uploadedFileToMultipartFile(passportCEO);
    }

    public MultipartFile getDecisionAppointingCeoMultipartFile() {
        return uploadedFileToMultipartFile(decisionAppointingCeo);
    }

    public MultipartFile getBalanceSheetMultipartFile() {
        return uploadedFileToMultipartFile(balanceSheet);
    }

    public MultipartFile getPdfReportMultipartFile() {
        return uploadedFileToMultipartFile(pdfReport);
    }

    private MultipartFile uploadedFileToMultipartFile(UploadedFile uploadedFile) {
        if (uploadedFile != null && uploadedFile.getData() != null) {
            return new MultipartFileAdapter(uploadedFile);
        }
        return null;
    }

}
