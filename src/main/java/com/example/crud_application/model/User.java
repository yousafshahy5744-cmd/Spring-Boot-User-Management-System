package com.example.crud_application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
public class User {

    public enum  AccountStatus{
        Approved , Pending , Rejected
    }
    public enum Role{
        USER, ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_NAME")
    private String userName;
    @Column(unique = true, name = "USER_CNIC")
    private String userCNIC;
    @Column(unique = true, name = "EMAIL")
    private String email;
    @NotEmpty(message = "Required")
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "USER_AGE")
    private int userAge;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role ;
}


