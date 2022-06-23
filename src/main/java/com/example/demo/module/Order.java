package com.example.demo.module;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private int odersID;
    private String ordersNumber;
    private Date ordersDate;
    private String additionalAddress;
    private Date shippedDate;
    @OneToOne
    @JoinColumn(name = "cartID")
    private shoppingcart shoppingcart;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<Item> orderItems = new HashSet<>();

    public void add(Item item) {

        if (item != null) {
            if (shoppingcart == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
