package com.ce.um.gestion_usuarios.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ce.um.gestion_usuarios.model.entity.UserEntity;

public interface UserDao  extends CrudRepository<UserEntity, Integer>{

    Optional<UserEntity> findByUsername(String username);

}
