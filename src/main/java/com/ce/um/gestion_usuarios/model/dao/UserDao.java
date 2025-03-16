package com.ce.um.gestion_usuarios.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.ce.um.gestion_usuarios.model.entity.User;

public interface UserDao  extends CrudRepository<User, Integer>{

}
