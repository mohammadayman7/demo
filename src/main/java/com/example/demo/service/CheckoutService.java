package com.example.demo.service;

import com.example.demo.DTO.Purchase;
import com.example.demo.DTO.PurchaseResponse;
import com.example.demo.module.Customer;
import com.example.demo.module.Item;
import com.example.demo.module.Order;
import com.example.demo.repository.customerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutService {
    customerRepository customerRepository;
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
       // order.setOrdersNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<Item> orderItems = purchase.getItem();
        //orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setAdditionalAddress(purchase.getAdditionalAddress());
        order.setShippedDate(purchase.getShippedDate());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            // we found them ... let's assign them accordingly
            customer = customerFromDB;
        }

       // customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }

}
