package com.example.demo.DTO;

import com.example.demo.module.Customer;
import com.example.demo.module.Item;
import com.example.demo.module.Order;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class Purchase {
    private int cartID;
    private Set<Item> item;
    private Customer customer;
    private Order Order;
    private String additionalAddress;
    private Date shippedDate;
}
