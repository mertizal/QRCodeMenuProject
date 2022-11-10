package com.example.QRCodeMenu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    private Long id;
    private String name;
    @Column(name = "passowrd_hash")
    private String password;
    @Column(unique = true)
    private String email;
    private String accessToken;

}
