package com.example.netcracker.homework6.service.impl;

import com.example.netcracker.homework6.model.entity.Customer;
import com.example.netcracker.homework6.repository.CustomerRepository;
import com.example.netcracker.homework6.service.api.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerServiceImpl extends EntityServiceImpl<Customer> implements CustomerService {

    private static final String TABLE_NAME = Customer.class.getAnnotation(Entity.class).name();
    private static final Set<String> FIELDS = Arrays.stream(Customer.class.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected UUID getEntityId(Customer entity) {
        return entity.getId();
    }

    @Override
    protected Set<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Set<String> findCustomerHomes() {
        return repository.findCustomerHomes();
    }

    @Override
    public List<Customer> findNameAndDiscountByHome(String home) {
        return repository.findNameAndDiscountByHome(home);
    }
}
