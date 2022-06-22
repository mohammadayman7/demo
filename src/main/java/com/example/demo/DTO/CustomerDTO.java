package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
   private Long customerid;
    private String fname;
    private  String lname;
    private Date dob;
    private  int phonenumber;
    private  String email;
    private String address;
}
