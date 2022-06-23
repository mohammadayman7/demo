package com.example.demo.module;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shoppingcart")
public class shoppingcart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int cartID;
    private int numOfItems;
    private double totalPrice;
    private int itemId;
    private double price;
    private String itemName;
//    @ManyToOne
//    @JoinColumn(name = "itemID")
//    private Item item;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;
//    @OneToOne()
//    @JoinColumn(name = "odersID")
//    private Order Order;


}
