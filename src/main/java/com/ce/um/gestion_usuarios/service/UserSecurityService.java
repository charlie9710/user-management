package com.ce.um.gestion_usuarios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.ce.um.gestion_usuarios.model.dao.UserDao;
import com.ce.um.gestion_usuarios.model.entity.UserEntity;
import com.ce.um.gestion_usuarios.model.entity.UserRoleEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserSecurityService implements UserDetailsService{

    private final UserDao userDao;

    @Autowired
    public UserSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= this.userDao.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String [] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();

    }

    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[] {"random_order"};
        }

        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String [] roles ){
        List <GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE" + role));

            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }

}
