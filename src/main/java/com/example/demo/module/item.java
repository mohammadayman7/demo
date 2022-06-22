package com.example.demo.module;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class item {
    @Id
    private int itemID;
    private String itemName;
    private Date postDate;
    private double price ;
    private Boolean isAuction;
    private int whatsappNumber;
    private String itemDescription;
    private String photoPath;
    private int quantity;
    private String auctionStatus;
    @OneToMany(mappedBy = "item")

    private List<com.example.demo.module.savedList> savedList;
    @OneToMany(mappedBy = "item")

    private List<shoppingcart>shoppingcarts ;
    @ManyToOne()
    @JoinColumn(name="sellerID")/**/
    private seller seller;
    @ManyToOne()
    @JoinColumn(name="categoryID")/**/
    private category category;
    @OneToOne
    @JoinColumn(name = "auctionID")
    private auction auction;



}
