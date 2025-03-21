package com.ce.um.gestion_usuarios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ce.um.gestion_usuarios.model.dao.UserDao;
import com.ce.um.gestion_usuarios.model.dto.UserDto;
import com.ce.um.gestion_usuarios.model.entity.UserEntity;
import com.ce.um.gestion_usuarios.service.IUserService;

import jakarta.transaction.Transactional;


@Service
public class UserImplService implements IUserService {

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public UserEntity save(UserDto userDto) {
        UserEntity user = UserEntity.builder()
                    .idUser(userDto.getIdUser())
                    .username(userDto.getUsername())
                    .name(userDto.getName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .build();     
        return userDao.save(user);
    }

    @Transactional
    @Override
    public UserEntity findById(Integer id) {
        return userDao.findById(id).orElse(null);

    }

    @Transactional
    @Override
    public void delete(UserEntity user) {
        userDao.delete(user);

    }


}
