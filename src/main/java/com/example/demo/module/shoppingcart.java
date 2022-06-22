package com.example.demo.module;

import javax.persistence.*;

@Entity
@Table(name = "shoppingcart")
public class shoppingcart {
    @Id
    private int cartID;
    private int numOfItems;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "itemID")
    private com.example.demo.module.item item;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private com.example.demo.module.customer customer;
    @OneToOne()
    @JoinColumn(name = "odersID")
    private com.example.demo.module.orders orders;


}
