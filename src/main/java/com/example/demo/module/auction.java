package com.example.demo.module;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auction")
public class auction {

    @Id
    private int auctionID;
    private Date startDate;
    private Date endDate;
    private double startPrice;
    private double endPrice;
    @OneToOne()
    @JoinColumn(name = "itemID")
    private com.example.demo.module.item item;
    @OneToMany(mappedBy = "offerID")

    private List<offer> offers;
}
