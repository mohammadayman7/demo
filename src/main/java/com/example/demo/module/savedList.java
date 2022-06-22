package com.example.demo.module;

import javax.persistence.*;

@Entity
@Table(name = "savedList")
public class savedList {
    @Id
    private int listID;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private com.example.demo.module.customer customer;
    @ManyToOne
    @JoinColumn(name = "itemID")
    private com.example.demo.module.item item;

}
