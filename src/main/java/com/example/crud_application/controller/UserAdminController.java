package com.example.crud_application.controller;

import com.example.crud_application.model.User;
import com.example.crud_application.service.implementation.UserAdminService;
import com.example.crud_application.service.userinterfaces.IUserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {

    @Autowired
    private IUserAdminService userAdminService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAll(){
        return userAdminService.getAll();
    }

    @PutMapping("/checkIsActive/{id}")
    public ResponseEntity<String> checkStatusIsActive(@PathVariable long id, @RequestParam boolean status){
        String message = userAdminService.checkStatus(id, status);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/checkRequest/{id}")
    public ResponseEntity<String> checkAccountRequest(@PathVariable long id, @RequestParam String accountStatus){
        String accountRequestMessage = userAdminService.accountRequest(id, accountStatus);
        return ResponseEntity.ok().body(accountRequestMessage);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> clearCompleteTable() {
        String message = userAdminService.deleteAllUsers();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/update-active-status")
    public ResponseEntity<String> bulkUpdateActiveUsers(@RequestParam String status) {
        String message = userAdminService.updateActiveUsersStatus(status);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/count")
    public Integer countUsers(@RequestParam String role) {
        return userAdminService.countUsers(role);
    }
}
