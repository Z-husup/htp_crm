package com.example.htp_crm.service;

import com.example.htp_crm.model.Application;
import com.example.htp_crm.service.interfaces.DocumentServiceInterface;
import com.example.htp_crm.util.TemplateProcessor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService implements DocumentServiceInterface {

    @Override
    public void generateExtractDocument(Application application) {
        try {
            // 1. Read template file
            InputStream templateInputStream = getClass().getResourceAsStream("/docx_templates/extract.docx");
            XWPFDocument templateDoc = new XWPFDocument(templateInputStream);

            // Date formatting
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
            String formattedDate = dateFormatter.format(new Date());

            // 2. Process the template
            Map<String, String> data = new HashMap<>();
            data.put("date", formattedDate);
            data.put("documentId", "№ 18-" + formattedDate);
            data.put("companyName", application.getCompanyNameEng()); // Use the relevant field
            data.put("council_1", "Калмаматова Э.Ш.");
            data.put("council_2", "Рабидинова М.А.");
            data.put("council_3", "Азимбаева Б.А.");
            data.put("council_4", "Муканбетовой Ш.М.");
            data.put("council_5", "Шатемирова К.Т.");

            // Replace placeholders with data
            TemplateProcessor.fillTemplate(templateDoc, data);

            // Save output
            try (FileOutputStream outputStream = new FileOutputStream("docx_output/extract.docx")) {
                templateDoc.write(outputStream);
            }

            System.out.println("Document generated successfully.");
        } catch (Exception e) {
            System.err.println("Error generating the document: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void generateTaxDocument(Application application) {
        try {
            // 1. Read template file
            InputStream templateInputStream = getClass().getResourceAsStream("/docx_templates/tax.docx");
            XWPFDocument templateDoc = new XWPFDocument(templateInputStream);

            // 2. Process the template
            Map<String, String> data = new HashMap<>();
            data.put("stateTaxLocation", application.getStateTaxLocation());
            data.put("socialFundLocation", application.getSocialFundLocation());
            data.put("registrationDate", application.getRegistrationNumber());
            data.put("regNumber", application.getRegistrationNumber());
            data.put("codeOKPO", application.getCodeOKPO());

            // Date formatting
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
            String formattedDate = dateFormatter.format(new Date());
            data.put("date", formattedDate);

            // Replace placeholders with data
            TemplateProcessor.fillTemplate(templateDoc, data);

            // Save output
            try (FileOutputStream outputStream = new FileOutputStream("docx_output/tax.docx")) {
                templateDoc.write(outputStream);
            }

            System.out.println("Document generated successfully.");
        } catch (Exception e) {
            System.err.println("Error generating the document: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void generateTreatyDocument(Application application) {
        try {
            // 1. Read template file
            InputStream templateInputStream = getClass().getResourceAsStream("/docx_templates/treaty.docx");
            XWPFDocument templateDoc = new XWPFDocument(templateInputStream);

            // 2. Process the template
            Map<String, String> data = new HashMap<>();
            data.put("residentName", application.getResidentName());
            data.put("residentNameShort", application.getResidentNameShort());
            data.put("bankName", application.getBankName());
            data.put("bankLocation", application.getBankLocation());
            data.put("paymentNumber", application.getPaymentNumber());
            data.put("bankId", application.getBankId()); // BIK
            data.put("residentPosition", application.getResidentJobPosition());

            // Date formatting
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
            String formattedDate = dateFormatter.format(new Date());
            data.put("date", formattedDate);

            // Replace placeholders with data
            TemplateProcessor.fillTemplate(templateDoc, data);

            // Save output
            try (FileOutputStream outputStream = new FileOutputStream("docx_output/treaty.docx")) {
                templateDoc.write(outputStream);
            }

            System.out.println("Document generated successfully.");
        } catch (Exception e) {
            System.err.println("Error generating the document: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
