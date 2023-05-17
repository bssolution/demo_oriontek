package com.oriontek.demo.service;

import java.util.List;

import com.oriontek.demo.dto.CustomerDTO;
import com.oriontek.demo.model.Customer;

public interface CustomerService {
    Customer createCustomer( CustomerDTO customerDTO);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, CustomerDTO customerDTO);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
}