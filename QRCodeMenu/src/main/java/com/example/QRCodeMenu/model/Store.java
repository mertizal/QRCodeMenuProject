package com.example.QRCodeMenu.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String description;
    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}
