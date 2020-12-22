package com.mediasoft.product.service;

import com.mediasoft.product.entity.Customer;
import com.mediasoft.product.entity.Region;

import java.util.List;

public interface ICustomerService {
    public List<Customer> listAllCustomer();
    public List<Customer> listCustomerByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer getCustomer(Long id);
}
