package com.example.crud_application.service.implementation;
import com.example.crud_application.dto.UpdateDto;
import com.example.crud_application.model.User;
import com.example.crud_application.repository.UserRepository;
import com.example.crud_application.service.userinterfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.time.LocalDateTime.*;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String updateUser(long id, UpdateDto updateDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User Don't Exists... ");
        }
        User existingUser = user.get();
        if (updateDto.getEmail() != null && updateDto.getPassword()!= null){
            existingUser.setEmail(updateDto.getEmail());
            existingUser.setPassword(updateDto.getPassword());
            existingUser.setUpdatedAt(now());
        }
        userRepository.save(existingUser);
        return "User Updated Successfully.....";

    }
    public String deleteById(long id) {
      User user = userRepository.findById(id).orElse(null);
      if (user == null){
          return "User not found...";
      }
      userRepository.deleteById(id);
      return "User Deleted Successfully.....";
    }

}