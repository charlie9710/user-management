package com.ce.um.gestion_usuarios.service;

import com.ce.um.gestion_usuarios.model.dto.UserDto;
import com.ce.um.gestion_usuarios.model.entity.User;

public interface IUserService {

    User save(UserDto user);

    User findById(Integer id);

    void delete(User user);


}
