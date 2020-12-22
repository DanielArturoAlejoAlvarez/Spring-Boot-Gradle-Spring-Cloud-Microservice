package com.mediasoft.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediasoft.product.entity.Customer;
import com.mediasoft.product.entity.Region;
import com.mediasoft.product.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(@RequestParam(name = "regionId", required = false) Long regionId) {
        List<Customer> customers = new ArrayList<>();
        if (null==regionId) {
            customers = service.listAllCustomer();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else {
            Region region = new Region();
            region.setId(regionId);
            customers = service.listCustomerByRegion(region);
            if (null==customers) {
                log.error("Customers with Region id {} not found.", regionId);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(name = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        log.info("Fetching Customer with id {}", id);
        Customer customer = service.getCustomer(id);
        if (null==customer) {
            log.error("Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, BindingResult result) {
        log.info("Creating Customer : {}", customer);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Customer customerDB = service.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        log.info("Updating Customer with id {}", id);
        Customer currentCustomer = service.getCustomer(id);
        if (null==currentCustomer) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer = service.updateCustomer(customer);
        return ResponseEntity.ok(currentCustomer);
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(Long id) {
        log.info("Fetching & Deleting Customer with id {}", id);
        Customer customer = service.getCustomer(id);
        if (null==customer) {
            log.error("Unable to delete. Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customer = service.deleteCustomer(customer);
        return ResponseEntity.ok(customer);
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
