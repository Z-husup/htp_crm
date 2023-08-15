package com.example.htp_crm.model;

import com.example.htp_crm.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated
    private UserType userType;

    private String username;

    private String password;

    private String fullname;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

}
