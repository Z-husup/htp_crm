package com.example.htp_crm.service;

import com.example.htp_crm.dto.ApplicationDto;
import com.example.htp_crm.dto.FounderDto;
import com.example.htp_crm.model.*;
import com.example.htp_crm.model.enums.ApplicationStatus;
import com.example.htp_crm.model.enums.ApplicationType;
import com.example.htp_crm.model.enums.Vote;
import com.example.htp_crm.repository.ApplicationRepository;
import com.example.htp_crm.service.interfaces.ApplicationServiceInterface;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        application.setPdfReport(generateApplicationPdf(application));

        applicationRepository.save(application);

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

        return applicationRepository.save(application);
    }

    @Override
    public UploadedFile generateApplicationPdf(Application application1) {

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDFont font = PDType0Font.load(document, new File("font/dejavusansbold.ttf"));

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 9);

            int yStart = 720;
            int yLineHeight = 20;

            contentStream.beginText();
            contentStream.newLineAtOffset(50, yStart);
            contentStream.showText("Application Details:");
            contentStream.newLineAtOffset(0, -yLineHeight);


            contentStream.showText("Company Name (Rus): " + application1.getCompanyNameRus());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Company Name (Eng): " + application1.getCompanyNameEng());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Registration Date: " + application1.getRegistrationDate());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Resident Job Position: " + application1.getResidentJobPosition());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Resident Name: " + application1.getResidentName());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Resident Name (Short): " + application1.getResidentNameShort());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Registration Number: " + application1.getRegistrationNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Code OKPO: " + application1.getCodeOKPO());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Bank Name: " + application1.getBankName());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Bank Account Number: " + application1.getBankAccountNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Payment Number: " + application1.getPaymentNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Bank Location: " + application1.getBankLocation());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("State Tax Location: " + application1.getStateTaxLocation());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Social Fund Location: " + application1.getSocialFundLocation());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Business Description: " + application1.getBusinessDescription());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Market Description: " + application1.getMarketDescription());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Business Characteristics: " + application1.getBusinessCharacteristics());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Company Email: " + application1.getCompanyEmail());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Company Number: " + application1.getCompanyNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Company Website: " + application1.getCompanyWebsite());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Director Full Name: " + application1.getDirectorFullName());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Director Number: " + application1.getDirectorNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Director Email: " + application1.getDirectorEmail());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Accountant Full Name: " + application1.getAccountantFullName());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Accountant Number: " + application1.getAccountantNumber());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Accountant Email: " + application1.getAccountantEmail());
            contentStream.newLineAtOffset(0, -yLineHeight);

            // Display information about uploaded files
            contentStream.showText("Articles of Association File: " + application1.getArticlesOfAccociation());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Passport Founders File: " + application1.getPassportFounders());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Passport CEO File: " + application1.getPassportCEO());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Decision Appointing CEO File: " + application1.getDecisionAppointingCeo());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("Balance Sheet File: " + application1.getBalanceSheet());
            contentStream.newLineAtOffset(0, -yLineHeight);

            contentStream.showText("PDF Report File: " + application1.getPdfReport());
            contentStream.newLineAtOffset(0, -yLineHeight);

            // Display information about founders if not null
            for (Founder founder : application1.getFounders()) {
                if (founder.getFounderFullName() != null && founder.getFounderCitizenship() != null && founder.getFounderNumber() != null) {
                    contentStream.showText("Founder: " + founder.getFounderFullName() + ", Citizenship: " + founder.getFounderCitizenship() + ", Number: " + founder.getFounderNumber());
                    contentStream.newLineAtOffset(0, -yLineHeight);
                }
            }
            // Display information about examination votes if not null
            for (UserApplicationVote vote : application1.getVotes()) {
                if (vote.getUser() != null && vote.getVote() != null) {
                    contentStream.showText("Vote by Expert: " + vote.getUser().getUsername() + ", Vote: " + vote.getVote());
                    contentStream.newLineAtOffset(0, -yLineHeight);
                }
            }

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

//            Saves in static.files
            String filePath = "src/main/resources/static.files/application_report.pdf"; // Specify the folder path and filename
            try {
                // Write the byte data to the specified file
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write(pdfBytes);
                fileOutputStream.close();
                // Create an UploadedFile instance with file details
                UploadedFile uploadedFile = new UploadedFile();
                uploadedFile.setFileName("application.pdf");
                uploadedFile.setContentType("application/pdf");
                uploadedFile.setData(pdfBytes);
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error saving file: " + e.getMessage());
            }
//            Saves in static.files

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

    public static Application fromDtoToEntity(ApplicationDto applicationDto) {
        Application application = new Application();
        BeanUtils.copyProperties(applicationDto, application);
        // Map complex properties
        application.setArticlesOfAccociation(applicationDto.getArticlesOfAccociationUploadedFile());
        application.setPassportFounders(applicationDto.getPassportFoundersUploadedFile());
        application.setPassportCEO(applicationDto.getPassportCEOUploadedFile());
        application.setDecisionAppointingCeo(applicationDto.getDecisionAppointingCeoUploadedFile());
        application.setBalanceSheet(applicationDto.getBalanceSheetUploadedFile());
        // Map founders
        Set<Founder> founders = new HashSet<>();
        for (FounderDto founderDto : applicationDto.getFounders()) {
            founders.add(founderDto.toFounderEntity());
        }
        application.setFounders(founders);
        return application;
    }

    public static ApplicationDto fromEntityToDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        BeanUtils.copyProperties(application, applicationDto);
        // Map complex properties
        applicationDto.setArticlesOfAccociation(application.getArticlesOfAccociationMultipartFile());
        applicationDto.setPassportFounders(application.getPassportFoundersMultipartFile());
        applicationDto.setPassportCEO(application.getPassportCEOMultipartFile());
        applicationDto.setDecisionAppointingCeo(application.getDecisionAppointingCeoMultipartFile());
        applicationDto.setBalanceSheet(application.getBalanceSheetMultipartFile());
        // Map founders
        Set<FounderDto> founderDtos = new HashSet<>();
        for (Founder founder : application.getFounders()) {
            founderDtos.add(founder.toFounderDto());
        }
        applicationDto.setFounders(founderDtos);

        return applicationDto;
    }

}
