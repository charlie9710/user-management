package com.ce.um.gestion_usuarios.model.dto;



import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto implements Serializable{

    private Integer idUser;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private LocalDateTime registerDate;

}
