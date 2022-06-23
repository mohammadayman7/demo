package com.example.demo.security;


import com.example.demo.module.Role;
import com.example.demo.repository.customerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private customerRepository CustomerRepository;

    public CustomUserDetailsService(customerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
//        Customer customer = CustomerRepository.findByEmail(Email)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with email:" + Email));
//        return new org.springframework.security.core.userdetails.User(customer.getEmail(),x`
//                customer.getPassword(), mapRolesToAuthorities(Arrays.asList(customer.getRoles())));
//    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return CustomerRepository.findByEmail(email);
    }
}
