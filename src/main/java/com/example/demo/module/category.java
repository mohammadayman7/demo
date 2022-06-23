package com.example.demo.module;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class category {
    @Id
    private int categoryID;
    private String categoryName;
    private int rootID;
    @OneToMany(mappedBy = "category")

    private List<Item> Items;

}
