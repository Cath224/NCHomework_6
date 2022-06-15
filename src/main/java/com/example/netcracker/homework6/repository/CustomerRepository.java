package com.example.netcracker.homework6.repository;

import com.example.netcracker.homework6.model.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CustomerRepository extends EntityRepository<Customer> {

    @Query(value = "SELECT DISTINCT HOME FROM CUSTOMER", nativeQuery = true)
    Set<String> findCustomerHomes();


    List<Customer> findNameAndDiscountByHome(@Param("home") String home);

}
