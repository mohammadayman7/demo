package com.example.demo.module;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offer")
public class offer {
    @Id
    private int offerID;
    private String offerDiscription;
    private int price;
    private Date offerDate;
    @ManyToOne
    @JoinColumn(name = "auctionID")
    private com.example.demo.module.auction auction;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
}
