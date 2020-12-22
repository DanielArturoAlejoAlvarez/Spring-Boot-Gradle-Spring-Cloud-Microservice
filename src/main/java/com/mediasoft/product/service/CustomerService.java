package com.mediasoft.product.service;

import com.mediasoft.product.entity.Customer;
import com.mediasoft.product.entity.Region;
import com.mediasoft.product.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository repo;

    @Override
    public List<Customer> listAllCustomer() {
        return repo.findAll();
    }

    @Override
    public List<Customer> listCustomerByRegion(Region region) {
        return repo.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = repo.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setStatus("CREATED");
        customerDB = repo.save(customer);
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (null==customerDB) {
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoURL(customer.getPhotoURL());
        return repo.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (null==customerDB) {
            return null;
        }
        customer.setStatus("DELETED");
        return repo.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return repo.findById(id).orElse(null);
    }
}
