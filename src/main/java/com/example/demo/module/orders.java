package com.example.demo.module;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "orders")
public class orders {
    @Id
    private int odersID;
    private int ordersNumber;
    private Date ordersDate;
    private String additionalAddress;
    private Date shippedDate;
    @OneToOne
    @JoinColumn(name = "cartID")
    private shoppingcart shoppingcart;
}
