package com.example.demo.registration;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class registrationRequest {
    private final String fname;
    private final String lname;
    private final Date dob;
    private final int phonenumber;
    private final String email;
    private final String address;
    private final String password;

}
