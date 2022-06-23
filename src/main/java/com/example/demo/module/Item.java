package com.example.demo.module;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    private int itemID;
    private String itemName;
    private Date postDate;
    private double price ;
    private String itemDescription;
    private String photoPath;
    private int quantity;
    @OneToMany(mappedBy = "item")

//    private List<savedList> savedList;
//    @OneToMany(mappedBy = "item")
//
//    private List<shoppingcart>shoppingcarts ;
    @ManyToOne()
    @JoinColumn(name="sellerID")/**/
    private seller seller;
    @ManyToOne()
    @JoinColumn(name="categoryID")/**/
    private category category;
    @OneToOne
    @JoinColumn(name = "auctionID")
    private auction auction;

//    @ManyToOne
//    @JoinColumn(name = "odersID")
//    private Order order;



}
