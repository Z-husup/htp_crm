package com.example.htp_crm.service;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.model.Application;
import com.example.htp_crm.model.UploadedFile;
import com.example.htp_crm.model.User;
import com.example.htp_crm.model.UserApplicationVote;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.model.enums.Vote;
import com.example.htp_crm.repository.ApplicationRepository;
import com.example.htp_crm.service.interfaces.ApplicationServiceInterface;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

        application.setPdfReport(generateApplicationPdf(application));

        applicationRepository.save(application);

        return null;
    }

    @Override
    public UploadedFile generateApplicationPdf(Application application) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            int yStart = 700;
            int yLineHeight = 20;

            contentStream.beginText();
            contentStream.newLineAtOffset(50, yStart);
            contentStream.showText("Application Details:");
            contentStream.newLine();

            contentStream.showText("Company Name (Rus): " + application.getCompanyNameRus());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Company Name (Eng): " + application.getCompanyNameEng());
            contentStream.newLineAtOffset(0, -yLineHeight);

            // Add more lines for other fields...

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName("application.pdf");
            uploadedFile.setContentType("application/pdf");
            uploadedFile.setData(pdfBytes);

            return uploadedFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Application updateApplication(Long applicationId, Application updatedApplication) {
        return null;
    }

    @Override
    @Transactional
    public void approveApplication(Application application, User expert) {
        addVote(application, expert, Vote.APPROVE);
    }

    @Override
    @Transactional
    public void denyApplication(Application application, User expert) {

        addVote(application, expert, Vote.DENY);
    }

    @Override
    @Transactional
    public void postponeApplication(Application application, User expert) {
        addVote(application, expert, Vote.POSTPONE);
    }

    @Override
    public void addVote(Application application, User user, Vote vote) {
        UserApplicationVote userApplicationVote = new UserApplicationVote();
        userApplicationVote.setUser(user);
        userApplicationVote.setApplication(application);
        userApplicationVote.setVote(vote);

        application.getVotes().add(userApplicationVote);

        // Update approval and denial counts based on the added vote
        int approvedCount = 0;
        int deniedCount = 0;
        for (UserApplicationVote vote2 : application.getVotes()) {
            if (vote2.getVote() == Vote.APPROVE) {
                approvedCount++;
            } else if (vote2.getVote() == Vote.DENY) {
                deniedCount++;
            }
        }

        if (approvedCount >= 3) {
            application.setApplicationStatus(ApplicationStatus.APPROVED);
        } else if (deniedCount >= 3) {
            application.setApplicationStatus(ApplicationStatus.DENIED);
        }

        // Update database
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long applicationId) {

    }
}
