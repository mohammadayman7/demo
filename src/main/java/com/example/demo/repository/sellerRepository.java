package com.example.demo.repository;

import com.example.demo.module.seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface sellerRepository
        extends JpaRepository<seller,Long> {
    Optional<seller> findByEmail(String email);
    Boolean existsByEmail(String email);
}
