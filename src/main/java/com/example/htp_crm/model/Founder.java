package com.example.htp_crm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Founder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String founderFullName;

    private String founderNumber;

    private String founderCitizenship;

    private String percentage;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

}
