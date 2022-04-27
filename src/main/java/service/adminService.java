package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.adminRepository;

@Service
public class adminService {
    @Autowired
    adminRepository repository;

}
