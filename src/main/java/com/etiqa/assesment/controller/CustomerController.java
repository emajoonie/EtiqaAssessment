package com.etiqa.assesment.controller;

import com.etiqa.assesment.exceptions.NotFoundException;
import com.etiqa.assesment.model.Customer;
import com.etiqa.assesment.model.Response;
import com.etiqa.assesment.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<Response<List<Customer>>> getCustomers() {
        return sendSuccessResponse(service.getCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Response<Customer>> getCustomer(@PathVariable int customerId) {
        logger.info("Request: customerId:"+ customerId);
        Customer customer = service.getCustomer(customerId);
        if (customer != null)
            return sendSuccessResponse(customer);
         else
            throw new NotFoundException("No Customer ID Found");

    }

    @PostMapping
    public ResponseEntity<Response<Customer>> createCustomer(@Valid @RequestBody Customer customer) {
        logger.info("Request:"+ dataToString(customer));
        service.createCustomer(customer);
        return sendSuccessResponse(customer,"Successfully added new customer");
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Response<Customer>> updateCustomer(@PathVariable int customerId,@Valid @RequestBody Customer customer) {
        logger.info("Request:"+ dataToString(customer));
        service.updateCustomer(customerId,customer);
        return sendSuccessResponse(customer,"customerId="+customerId +" successfully updated");
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Response<Void>> deleteCustomer(@PathVariable int customerId) {
        logger.info("Request: customerId:"+ customerId);
        service.deleteCustomer(customerId);
        return sendSuccessResponse("Customer deleted successfully");
    }
}
