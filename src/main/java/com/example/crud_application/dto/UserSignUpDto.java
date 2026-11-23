package com.example.crud_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUpDto {
    private String userName;
    private String userCNIC;
    @Size(min = 8 , max = 16, message = "kindly choose password between 8 to 16 chars")
    private String password;
    @Email(message = "invalid Email format.")
    private String email;
    private String mobileNumber;
    private String department;
    private String country;
    private int userAge;

}
