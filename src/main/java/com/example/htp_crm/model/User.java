package com.example.htp_crm.model;

import com.example.htp_crm.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    private String username;

    private String password;

    private String fullname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserApplicationVote> votes = new HashSet<>();

}
