package com.example.demo.repository;

import com.example.demo.module.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepository
        extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
