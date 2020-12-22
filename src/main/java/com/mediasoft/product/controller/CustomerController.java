package com.mediasoft.product.controller;

import com.mediasoft.product.entity.Customer;
import com.mediasoft.product.entity.Region;
import com.mediasoft.product.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private ICustomerService service;

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

}
