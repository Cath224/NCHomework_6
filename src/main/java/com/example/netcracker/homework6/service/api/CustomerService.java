package com.example.netcracker.homework6.service.api;

import com.example.netcracker.homework6.model.entity.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService extends EntityService<Customer> {

    Set<String> findCustomerHomes();
    List<Customer> findNameAndDiscountByHome(String home);

}
