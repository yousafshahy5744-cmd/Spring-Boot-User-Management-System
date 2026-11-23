package com.example.crud_application.dto;
import com.example.crud_application.model.User;
import lombok.*;

@Data

@Setter
@Getter
public class UserResponseDto {
    private String token;
    private String userName;
    private String userCNIC;
    private String email;
    private User.Role role;
//
    public UserResponseDto(String token, String userName, String userCNIC, String email, User.Role role) {
    this.token=token;
    this.userName = userName;
    this.userCNIC = userCNIC;
    this.email =email;
    this.role = role;

    }
}
