package com.example.crud_application.service.implementation;

import com.example.crud_application.model.User;
import com.example.crud_application.repository.UserRepository;
import com.example.crud_application.service.userinterfaces.IUserAdminService;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
@Service
public class UserAdminService implements IUserAdminService {

    @Autowired
    private UserRepository userRepository;

    public String checkStatus(long id, boolean status) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User Not found..");
        }
        User existingUser = user.get();
        existingUser.setIsActive(status);
        existingUser.setUpdatedAt(now());
        userRepository.save(existingUser);
        return "IsActive Status updated to "+status;
    }

    public String accountRequest(long id , String accountStatus){
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()){
                return "User Not Found...";
            }
            User exsitingUser = user.get();
            if (exsitingUser.getIsActive()== false){
                return "Kindly active your account first...";
            }
            exsitingUser.setStatus(User.AccountStatus.valueOf(accountStatus));
            exsitingUser.setUpdatedAt(now());
            userRepository.save(exsitingUser);
            return "Account Request Successfully "+accountStatus;
        }catch (Exception e){
            throw new RuntimeException("Request Not Updated..");
        }
    }

    public String deleteAllUsers() {
        userRepository.deleteAllInBatch();
        return "All users deleted successfully from the database!";
    }

    public String updateActiveUsersStatus(String newStatus) {
        try {
            int updatedCount = userRepository.updateStatusForActivesUsers(User.AccountStatus.valueOf(newStatus.trim()), now());
            if (updatedCount == 0) {
                return "No active users found to update.";
            }
            return updatedCount + " active users updated successfully to " + newStatus;
        } catch (Exception e) {
            return "Invalid status! Please enter Pending, Approved, or Rejected.";
        }
    }

    public ResponseEntity<List<User>> getAll() {
       try {
           List<User> user = userRepository.findAll();
           return new ResponseEntity<>(user, HttpStatus.OK);
       }
       catch (Exception e){
           throw new RuntimeException("No list of users found");
       }
    }

    public Integer countUsers(String role) {
        return userRepository.countUsersByRole(role);
    }
}
