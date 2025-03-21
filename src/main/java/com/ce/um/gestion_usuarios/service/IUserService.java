package com.ce.um.gestion_usuarios.service;

import com.ce.um.gestion_usuarios.model.dto.UserDto;
import com.ce.um.gestion_usuarios.model.entity.UserEntity;

public interface IUserService {

    UserEntity save(UserDto user);

    UserEntity findById(Integer id);

    void delete(UserEntity user);


}
