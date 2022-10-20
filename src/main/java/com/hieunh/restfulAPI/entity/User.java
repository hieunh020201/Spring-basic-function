package com.hieunh.restfulAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQuery(
        name = "getUserInfo",
        query = "Select * From user Where id = ?1",
        resultClass = User.class
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "role", columnDefinition = "varchar(255) default 'USER'")
    private String role;
}
