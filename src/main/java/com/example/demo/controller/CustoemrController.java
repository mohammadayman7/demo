package com.example.demo.controller;


import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.registrationRequest;
import com.example.demo.module.Role;
import com.example.demo.module.customer;
import com.example.demo.payload.JWTAuthResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.repository.customerRepository;
import com.example.demo.repository.roleRepository;
import com.example.demo.repository.sellerRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustoemrController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    customerRepository customerRepository;

    @Autowired
    private roleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    sellerRepository sellerRepository;

    customerService customerService;

    // "REST API to Register or Signup user to Blog app"
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

   // "REST API to Signin or Login user to Blog app"
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody registrationRequest registrationRequest){
//        boolean isValidEmail =
//                emailValidator.test(registrationRequest.getEmail());
//        if (!isValidEmail){
//            return new ResponseEntity<>("email not valid", HttpStatus.BAD_REQUEST);
//        }

        // add check for email exists in DB
        if(customerRepository.existsByEmail(registrationRequest.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        customer  customer = new customer();
        customer.setFname((registrationRequest.getFname()));
        customer.setLname(registrationRequest.getLname());
        customer.setEmail(registrationRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        Role roles = roleRepository.findByName("Customer");
        customer.setRoles(roles);

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updatCustomer(
             @RequestBody CustomerDTO customerDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerService.updatCustomer(customerDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(name = "id") long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    //    @PostMapping
//    public ResponseEntity<CustomerDTO> createCustomer( @RequestBody CustomerDTO customerDto) {
//        if (customerDto.getId() != null) {
//
//            throw new BadRequestException(CustomerController.class.getSimpleName(), "Id");
//        }
//        return new ResponseEntity(customerService.createCustomer(customerDto), HttpStatus.CREATED);
//    }

}
