package com.example.QRCodeMenu.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "category")

public class Category {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "store_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

//    @OneToMany
//    @JoinColumn(name = "category_id")
//    private List<Product> productList;
}
