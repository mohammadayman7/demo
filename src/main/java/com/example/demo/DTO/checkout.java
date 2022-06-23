package com.example.demo.DTO;

import com.example.demo.module.Order;
import com.example.demo.module.shoppingcart;
import lombok.Data;

import java.util.List;

@Data
public class checkout {
    List<shoppingcart> checkout;
    Order order;

}
