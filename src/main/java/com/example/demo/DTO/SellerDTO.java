package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class SellerDTO {
    private Long sellerid;
    private String fname;
    private  String lname;
    private Date dob;
    private  int phonenumber;
    private  String email;
    private String address;
}
