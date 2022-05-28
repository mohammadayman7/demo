package com.example.demo.registration;

import com.example.demo.module.customer;
import com.example.demo.module.seller;
import com.example.demo.service.customerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.service.sellerService;
@Service
@AllArgsConstructor
public class registrationService {
    customerService customerService;
    sellerService sellerService;
    emailValidator emailValidator;
    public  String registerCustomer(registrationRequest request) {
       boolean isValidEmail =
               emailValidator.test(request.getEmail());
       if (!isValidEmail){
           return "email not valid";
       }

           return customerService.signUpCustomer(
                   new customer(
                           request.getFname(),
                           request.getLname(),
                           request.getDob(),
                           request.getPhonenumber(),
                           request.getEmail(),
                           request.getPassword(),
                           request.getAddress()



                   )
           );






    }

    public String registerSeller(registrationRequest request) {
        boolean isValidEmail =
                emailValidator.test(request.getEmail());
        if (!isValidEmail){
            return "email not valid";
        }
        return sellerService.signUpSeller(
                new seller(
                        request.getFname(),
                        request.getLname(),
                        request.getDob(),
                        request.getPhonenumber(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getAddress()



                )
        );
    }
}
