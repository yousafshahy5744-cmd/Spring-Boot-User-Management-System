package com.example.crud_application.service.userinterfaces;

import com.example.crud_application.dto.LoginRequestDto;
import com.example.crud_application.dto.UserResponseDto;
import com.example.crud_application.dto.UserSignUpDto;
import org.springframework.http.ResponseEntity;

public interface IAuthorizationService {

    String signupUser(UserSignUpDto userSignUpDto);

    ResponseEntity<UserResponseDto> loginUser(LoginRequestDto loginRequestDto);
}
