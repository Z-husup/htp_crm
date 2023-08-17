package com.example.htp_crm.service.interfaces;

import com.example.htp_crm.model.Application;

public interface DocumentServiceInterface {
    void generateExtractDocument(Application application);

    void generateTaxDocument(Application application);

    void generateTreatyDocument(Application application);
}
