package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="api/registration")
@AllArgsConstructor
public class registrationController {
    registrationService registrationService;
    @PostMapping("/customer")
public String registerCustomer(@RequestBody registrationRequest request){
    return registrationService.registerCustomer(request);
}
    @PostMapping("/seller")
    public String registerSeller(@RequestBody registrationRequest request){
        return registrationService.registerSeller(request);
    }
}
