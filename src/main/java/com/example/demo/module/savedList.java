package com.example.demo.module;

import javax.persistence.*;

@Entity
@Table(name = "savedList")
public class savedList {
    @Id
    private int listID;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "itemID")
    private Item item;

}
