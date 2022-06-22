package com.example.demo.service;

import com.example.demo.DTO.SellerDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.module.seller;
import com.example.demo.repository.sellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class sellerService implements UserDetailsService {
    sellerRepository repository;

    public List<SellerDTO> getAllCustomers() {
        List<seller> sellers = repository.findAll();

        return sellers.stream().map(seller -> mapToDTO(seller)).collect(Collectors.toList());
    }


    public SellerDTO getsellerById(long id) {
        seller seller = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(seller);
    }


    public SellerDTO updatselelr(SellerDTO sellerDTO, long id) {
        // get Customer by id from the database
        seller seller = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("seller", "id", id));

        seller.setFname(sellerDTO.getFname());
        seller.setAddress(sellerDTO.getAddress());
        seller.setLname(sellerDTO.getLname());
        seller.setDob(sellerDTO.getDob());
        seller.setEmail(sellerDTO.getEmail());
        seller.setPhonenumber(sellerDTO.getPhonenumber());

        seller updateSeller = repository.save(seller);

        return mapToDTO(updateSeller);
    }


    public void deleteSellerById(long id) {
        // get Custome      r by id from the database
        seller seller = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        repository.delete(seller);
    }
    // convert Entity into DTO
    private SellerDTO mapToDTO(seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setSellerid(seller.getSellerid());
        sellerDTO.setFname(seller.getFname());
        sellerDTO.setAddress(seller.getAddress());
        sellerDTO.setLname(seller.getLname());
        sellerDTO.setDob(seller.getDob());
        sellerDTO.setEmail(seller.getEmail());
        sellerDTO.setPhonenumber(seller.getPhonenumber());

        return sellerDTO;
    }

    // convert DTO to entity
    private seller mapToEntity(SellerDTO sellerDTO) {
        seller seller = new seller();
        seller.setSellerid(sellerDTO.getSellerid());
        seller.setFname(sellerDTO.getFname());
        seller.setAddress(sellerDTO.getAddress());
        seller.setLname(sellerDTO.getLname());
        seller.setDob(sellerDTO.getDob());
        seller.setEmail(sellerDTO.getEmail());
        seller.setPhonenumber(sellerDTO.getPhonenumber());

        return seller;
    }











///////////////////////






    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG
            = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG , email)
                        ));
    }


    public String signUpSeller(seller seller){
        boolean customerExist =repository.
                findByEmail(seller.getEmail())
                .isPresent();
        if (customerExist){
            return "email alredy taken";
        }
        String cEncodePassword = bCryptPasswordEncoder
                .encode(seller.getPassword());
        seller.setPassword(cEncodePassword);
        repository.save(seller);
        //TODO send confirmation token
        return "it works";
    }
//    public status login(seller seller) {
//        List<seller> sellers = repository.findAll();
//        for (seller other : sellers) {
//            if (other.equals(seller)) {
//
//                repository.save(seller);
//                return status.SUCCESS;
//            }
//        }
//        return status.FAILURE;
//    }

}
