package com.example.htp_crm.dto;

import com.example.htp_crm.model.UploadedFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private Set<FounderDto> founders = new HashSet<>();

    public UploadedFile getArticlesOfAccociationUploadedFile() {
        return multipartFileToUploadedFile(articlesOfAccociation);
    }

    public UploadedFile getPassportFoundersUploadedFile() {
        return multipartFileToUploadedFile(passportFounders);
    }

    public UploadedFile getPassportCEOUploadedFile() {
        return multipartFileToUploadedFile(passportCEO);
    }

    public UploadedFile getDecisionAppointingCeoUploadedFile() {
        return multipartFileToUploadedFile(decisionAppointingCeo);
    }

    public UploadedFile getBalanceSheetUploadedFile() {
        return multipartFileToUploadedFile(balanceSheet);
    }

    private UploadedFile multipartFileToUploadedFile(MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(multipartFile.getOriginalFilename());
            uploadedFile.setContentType(multipartFile.getContentType());
            try {
                uploadedFile.setData(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return uploadedFile;
        }
        return null;
    }
}
