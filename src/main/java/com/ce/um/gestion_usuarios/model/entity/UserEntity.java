package com.ce.um.gestion_usuarios.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "users")
public class UserEntity  implements Serializable{

    @Id
    @Column(name = "id_users")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false ,  unique = true)
    private String username;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "register_date", updatable = false)
    private LocalDateTime registerDate;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;


    @PrePersist
    protected void onCreate() {
        registerDate = LocalDateTime.now();
    }
}
