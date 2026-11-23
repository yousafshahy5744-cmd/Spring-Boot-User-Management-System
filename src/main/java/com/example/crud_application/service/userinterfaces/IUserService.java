package com.example.crud_application.service.userinterfaces;

import com.example.crud_application.dto.UpdateDto;

public interface IUserService {

    String updateUser(long id, UpdateDto updateDto);

    String deleteById(long id);


}
