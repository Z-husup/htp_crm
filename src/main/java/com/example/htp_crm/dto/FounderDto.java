package com.example.htp_crm.dto;

import com.example.htp_crm.model.Founder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FounderDto {

    private String founderFullName;

    private String founderNumber;

    private String founderCitizenship;

    private String percentage;

    public Founder toFounderEntity() {
        Founder founder = new Founder();
        founder.setFounderFullName(this.founderFullName);
        founder.setFounderNumber(this.founderNumber);
        founder.setFounderCitizenship(this.founderCitizenship);
        founder.setPercentage(this.percentage);
        return founder;
    }

}
