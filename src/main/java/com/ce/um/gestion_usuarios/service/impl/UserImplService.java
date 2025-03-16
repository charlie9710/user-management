package com.ce.um.gestion_usuarios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ce.um.gestion_usuarios.model.dao.UserDao;
import com.ce.um.gestion_usuarios.model.dto.UserDto;
import com.ce.um.gestion_usuarios.model.entity.User;
import com.ce.um.gestion_usuarios.service.IUserService;

import jakarta.transaction.Transactional;


@Service
public class UserImplService implements IUserService {

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public User save(UserDto userDto) {
        User user = User.builder().
                    idUser(userDto.getIdUser())
                    .name(userDto.getName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .registerDate(userDto.getRegisterDate())
                    .build();     
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User findById(Integer id) {
        return userDao.findById(id).orElse(null);

    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);

    }


}
