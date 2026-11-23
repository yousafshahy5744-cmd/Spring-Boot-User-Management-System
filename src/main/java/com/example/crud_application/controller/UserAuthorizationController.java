package com.example.crud_application.controller;

import com.example.crud_application.dto.LoginRequestDto;
import com.example.crud_application.dto.UserResponseDto;
import com.example.crud_application.dto.UserSignUpDto;
import com.example.crud_application.service.userinterfaces.IAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UserAuthorizationController {

    @Autowired
     private IAuthorizationService iAuthorizationService;

    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@Valid @RequestBody UserSignUpDto userSignUpDto){
        String signUpMessage = iAuthorizationService.signupUser(userSignUpDto);
        return ResponseEntity.ok().body(signUpMessage);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> userLogin(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return iAuthorizationService.loginUser(loginRequestDto);
    }

}
