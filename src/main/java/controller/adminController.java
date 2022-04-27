package controller;

import module.admin;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.adminService;

@RestController
@RequestMapping("/api/admin")
public class adminController {

    private adminService adminService;
    @PostMapping
public admin checkAdmin(admin admin){
   return adminService;

}
}
