package com.example.demo.service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.module.Customer;
import com.example.demo.module.Item;
import com.example.demo.module.shoppingcart;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.customerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class customerService implements UserDetailsService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    customerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> Customers = customerRepository.findAll();

        return Customers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());
    }


    public CustomerDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(customer);
    }


    public CustomerDTO updatCustomer(CustomerDTO customerDto, long id) {
        // get Customer by id from the database
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customer.setFname(customerDto.getFname());
        customer.setAddress(customerDto.getAddress());
        customer.setLname(customerDto.getLname());
        customer.setDob(customerDto.getDob());
        customer.setEmail(customerDto.getEmail());
        customer.setPhonenumber(customerDto.getPhonenumber());

        Customer updateCustomer = customerRepository.save(customer);

        return mapToDTO(updateCustomer);
    }


    public void deleteCustomerById(long id) {
        // get Custome      r by id from the database
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }

    // convert Entity into DTO
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setCustomerid(customer.getCustomerid());
        customerDto.setFname(customer.getfName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setLname(customer.getlName());
        customerDto.setDob(customer.getDob());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhonenumber(customer.getPhonenumber());

        return customerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setCustomerid(customerDto.getCustomerid());
        customer.setFname(customerDto.getFname());
        customer.setAddress(customerDto.getAddress());
        customer.setLname(customerDto.getLname());
        customer.setDob(customerDto.getDob());
        customer.setEmail(customerDto.getEmail());
        customer.setPhonenumber(customerDto.getPhonenumber());

        return customer;
    }


    private final customerRepository repository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG
            = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public shoppingcart addToCart(Item item, Long id) {
        shoppingcart shoppingcart= new shoppingcart();

        shoppingcart.setItemId(item.getItemID());
        shoppingcart.setItemName(item.getItemName());
        shoppingcart.setPrice(item.getPrice());

        Customer customer= new Customer();
        customer.setCustomerid(id);

        shoppingcart.setCustomer(customer);


        return cartRepository.save(shoppingcart);

    }

    public List<shoppingcart> getItemFromCart(Long id) {
        return cartRepository.findByCustomer_Customerid(id);
    }


//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//        return repository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException(
//                                String.format(USER_NOT_FOUND_MSG , email)
//                        ));
//    }
//    public String signUpCustomer(Customer customer){
//        boolean customerExist =repository.
//                findByEmail(customer.getEmail())
//                    .isPresent();
//        if (customerExist){
//            return "email alredy taken";
//        }
//       String cEncodePassword = bCryptPasswordEncoder
//               .encode(customer.getPassword());
//        customer.setPassword(cEncodePassword);
//        repository.save(customer);
//        //TODO send confirmation token
//        return "it works";
//    }

//    public status login(customer customer) {
//        List<customer> customers = repository.findAll();
//        for (customer other : customers) {
//            if (other.equals(customer)) {
//
//                repository.save(customer);
//                return status.SUCCESS;
//            }
//        }
//        return status.FAILURE;
//    }

}
