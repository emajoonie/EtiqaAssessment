package com.etiqa.assesment.service;

import com.etiqa.assesment.controller.CustomerController;
import com.etiqa.assesment.exceptions.ServiceException;
import com.etiqa.assesment.exceptions.NotFoundException;
import com.etiqa.assesment.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiqa.assesment.repository.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        try {
            return repository.findAll();
        } catch(Exception e){
            throw new ServiceException("Failed to retrieve customers",e);
        }
    }

    public Customer getCustomer(int customerId) {
        try {
            return repository.findById(customerId).orElse(null);
        } catch (Exception e){
            throw new ServiceException("Failed to retrieve customer by ID: " + customerId, e);
        }
    }

    public void createCustomer(Customer customer) {
        try {
             repository.save(customer);
        } catch(Exception e){
            throw new ServiceException("Failed to create customer");
        }
    }

    public void updateCustomer(int customerId, Customer customer) {
        if(!repository.existsById(customerId)) throw new NotFoundException("No Customer ID Found");

        try{
            customer.setCustomerId(customerId);
            repository.save(customer);
        } catch(Exception e){
            throw new ServiceException("Failed to update customer");
        }
    }

    public void deleteCustomer(int customerId) {
        if (!repository.existsById(customerId)) throw new NotFoundException("No Customer ID Found, Unable to delete");
        try {
            repository.deleteById(customerId);
        }catch (Exception e){
            throw new ServiceException("Failed to delete customer with ID:"+ customerId,e);
        }
    }
}
