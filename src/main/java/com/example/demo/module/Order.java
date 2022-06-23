package com.example.demo.module;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int odersid;
    private String additionalAddress;
    private Date shippedDate;
    @OneToMany
    private List<shoppingcart> shoppingcart;




}
