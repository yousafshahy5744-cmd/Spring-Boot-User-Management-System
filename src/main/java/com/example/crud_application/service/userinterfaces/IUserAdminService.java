package com.example.crud_application.service.userinterfaces;

import com.example.crud_application.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserAdminService {

    String checkStatus(long id, boolean status);

    String accountRequest(long id , String account_status);

    String deleteAllUsers();

    String updateActiveUsersStatus(String newStatus);

    ResponseEntity<List<User>> getAll();

    Integer countUsers(String role);
}
