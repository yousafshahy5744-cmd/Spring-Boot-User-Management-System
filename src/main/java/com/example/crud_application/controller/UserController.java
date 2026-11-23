package com.example.crud_application.controller;

import com.example.crud_application.dto.UpdateDto;
import com.example.crud_application.service.userinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/{id}/update")
    public ResponseEntity<String> userUpdateById(@PathVariable long id, @RequestBody UpdateDto updateDto){
        String updateMessage = userService.updateUser(id, updateDto);
        return ResponseEntity.ok().body(updateMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        String deleteMessage = userService.deleteById(id);
        return ResponseEntity.ok().body(deleteMessage);
    }



}
