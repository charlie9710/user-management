package com.ce.um.gestion_usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ce.um.gestion_usuarios.model.dao.UserDao;
import com.ce.um.gestion_usuarios.model.entity.User;

@Service
public class UserSecurityService implements UserDetailsService{

    @Autowired
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByUser();

    }

}
