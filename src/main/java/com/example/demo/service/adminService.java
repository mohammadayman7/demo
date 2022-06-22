package com.example.demo.service;

import com.example.demo.repository.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class adminService implements UserDetailsService {
    @Autowired
    adminRepository repository;
    private final static String USER_NOT_FOUND_MSG
            = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG , email)
                        ));

    }

//    public status login(admin admin) {
//        List<admin> admins = repository.findAll();
//        for (admin other : admins) {
//            if (other.equals(admin)) {
//
//                repository.save(admin);
//                return status.SUCCESS;
//            }
//        }
//        return status.FAILURE;
//    }
}
