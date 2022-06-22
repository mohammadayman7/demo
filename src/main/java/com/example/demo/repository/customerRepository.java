package com.example.demo.repository;

import com.example.demo.module.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface customerRepository
        extends JpaRepository<customer,Long> {
    Optional<customer> findByEmail(String email);
    Boolean existsByEmail(String email);


}
