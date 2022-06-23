package com.example.demo.module;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements UserDetails {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long customerid;

    private String fname;

    private String lname;

    private Date dob;

    private int phonenumber;

    private String email;

    private String password;

    private String address;

    @OneToMany(mappedBy = "customer")
    private List<savedList> savedLists;

    @OneToMany
    private List<shoppingcart> shoppingcarts;

    @OneToMany(mappedBy = "offerID")

    private List<offer> offers;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Role roles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();


    public void add(Order order) {

        if (order != null) {

            if (orders == null) {
                orders = new HashSet<>();
            }

            orders.add(order);
            order.setCustomer(this);
        }
    }

    public Customer(String fName, String lName, Date dob, int phoneNumber,
                    String email, String password, String address) {

        this.fname = fName;
        this.lname = lName;
        this.dob = dob;
        this.phonenumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;


    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getfName() {
        return fname;
    }

    public String getlName() {
        return lname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
