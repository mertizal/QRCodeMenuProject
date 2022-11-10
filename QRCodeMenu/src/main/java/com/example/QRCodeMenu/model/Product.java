package com.example.QRCodeMenu.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "product")
public class Product {

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String photo;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "store_id",referencedColumnName = "id")
    private Store store;

}
