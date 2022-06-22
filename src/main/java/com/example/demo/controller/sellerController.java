package com.example.demo.controller;

import com.example.demo.DTO.SellerDTO;
import com.example.demo.DTO.registrationRequest;
import com.example.demo.module.Role;
import com.example.demo.module.seller;
import com.example.demo.payload.JWTAuthResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.repository.sellerRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.sellerService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping(path = "api/seller")
public class sellerController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private com.example.demo.repository.roleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    sellerRepository sellerRepository;

    sellerService sellerService;

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
        if(sellerRepository.existsByEmail(registrationRequest.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        seller seller = new seller();
        seller.setFname((registrationRequest.getFname()));
        seller.setLname(registrationRequest.getLname());
        seller.setEmail(registrationRequest.getEmail());
        seller.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        Role roles = roleRepository.findByName("seller");
        seller.setRoles(roles);

        sellerRepository.save(seller);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<SellerDTO>> getAllsellers() {
        return ResponseEntity.ok(sellerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getsellerById(@PathVariable(name = "sellerid") long id) {
        return ResponseEntity.ok(sellerService.getsellerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDTO> updatseller(
            @RequestBody SellerDTO sellerDTO, @PathVariable(name = "sellerid") long id) {
        return new ResponseEntity<>(sellerService.updatselelr(sellerDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletesellerById(@PathVariable(name = "sellerid") long id){
        sellerService.deleteSellerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
