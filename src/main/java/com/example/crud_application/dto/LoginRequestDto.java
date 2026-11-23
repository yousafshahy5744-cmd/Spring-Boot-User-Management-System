package com.example.crud_application.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
