package com.example.demo.module;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name ="seller")
@Entity
public class seller implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long sellerid;

    private String fname;

    private  String lname;

    private Date dob;

    private  int phonenumber;

    private  String email;

    private String password;

    private String address;

    @OneToMany(mappedBy = "seller")

    private List<item> items;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Role roles;

    public seller(String fname, String lname, Date dob, int phonenumber, String email, String password, String address) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.phonenumber = phonenumber;
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

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
