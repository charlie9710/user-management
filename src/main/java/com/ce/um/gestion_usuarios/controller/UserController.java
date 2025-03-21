package com.ce.um.gestion_usuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ce.um.gestion_usuarios.model.dto.UserDto;
import com.ce.um.gestion_usuarios.model.entity.UserEntity;
import com.ce.um.gestion_usuarios.model.payload.ResponseMessage;
import com.ce.um.gestion_usuarios.service.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        UserEntity userSave = null;
        try {
            userSave = userService.save(userDto);
            userDto = UserDto.builder()
                        .idUser(userSave.getIdUser())
                        .username(userSave.getUsername())
                        .name(userSave.getName())
                        .lastName(userSave.getLastName())
                        .email(userSave.getEmail())
                        .registerDate(userSave.getRegisterDate())
                        .build();  
            return new ResponseEntity<>(ResponseMessage.builder()
                                        .message("Saved correctly")
                                        .object(userDto)
                                        .build(),HttpStatus.CREATED);
            
        } catch (DataAccessException e) {
            return new ResponseEntity<>(ResponseMessage.builder()
                                        .message(e.getMessage())
                                        .object(null)
                                        .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Integer id){

        UserEntity userUpdate = null;
        try {

            UserEntity findUser = userService.findById(id);

            if (findUser!=null){
                userDto.setIdUser(id);
                userUpdate = userService.save(userDto);
                userDto = UserDto.builder()
                        .idUser(userUpdate.getIdUser())
                        .username(userUpdate.getUsername())
                        .name(userUpdate.getName())
                        .lastName(userUpdate.getLastName())
                        .email(userUpdate.getEmail())
                        .registerDate(userUpdate.getRegisterDate())
                        .build();  
                return new ResponseEntity<>(ResponseMessage.builder()
                                            .message("Modified correctly")
                                            .object(userDto)
                                            .build(),HttpStatus.CREATED);

            }else{
                return new ResponseEntity<>(ResponseMessage.builder()
                                        .message("The user does not exist")
                                        .object(null)
                                        .build(), HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            return new ResponseEntity<>(ResponseMessage.builder()
                                        .message(e.getMessage())
                                        .object(null)
                                        .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    @DeleteMapping("user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            UserEntity userDelete = userService.findById(id);
            userService.delete(userDelete);
            return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {

            return new ResponseEntity<>(ResponseMessage.builder()
                                            .message(e.getMessage())
                                            .object(null)
                                            .build()
                                        , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Secured("ADMIN")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        UserEntity user = userService.findById(id);

        if (user == null ){
            return new ResponseEntity<>(ResponseMessage.builder()
                                        .message("User not found")
                                        .object(null)
                                        .build()
                                        , HttpStatus.METHOD_NOT_ALLOWED);

        }
        UserDto userDto = UserDto.builder()
                    .idUser(user.getIdUser())
                    .username(user.getUsername())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .registerDate(user.getRegisterDate())
                    .build();
        return new ResponseEntity<>(ResponseMessage.builder()
                                    .message("User obtained")
                                    .object(userDto)
                                    .build(), HttpStatus.OK);

    }

}
