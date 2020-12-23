package com.mediasoft.product.client;

import com.mediasoft.product.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

    @GetMapping(name = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);
}
