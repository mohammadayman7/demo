package com.example.demo.repository;


import com.example.demo.module.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface adminRepository
        extends JpaRepository<admin,Integer> {
    Optional<admin> findByEmail(String  email);
    Boolean existsByEmail(String email);


}
