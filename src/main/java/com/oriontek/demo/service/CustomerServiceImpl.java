package com.oriontek.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oriontek.demo.dto.CustomerDTO;
import com.oriontek.demo.model.Company;
import com.oriontek.demo.model.Customer;
import com.oriontek.demo.repository.CompanyRepository;
import com.oriontek.demo.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository ) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Company company = companyRepository.findById(customerDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + customerDTO.getCompanyId()));
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCompany(company);
        

        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return customer;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Company company = companyRepository.findById(customerDTO.getCompanyId())
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + customerDTO.getCompanyId()));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCompany(company);

        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapCustomerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCompany(customer.getCompany());
        customerDTO.setContacts(customer.getContacts());
        return customerDTO;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
}
