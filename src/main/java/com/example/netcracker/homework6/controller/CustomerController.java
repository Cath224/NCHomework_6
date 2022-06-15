package com.example.netcracker.homework6.controller;

import com.example.netcracker.homework6.mapper.CustomerMapper;
import com.example.netcracker.homework6.model.dto.CustomerDTO;
import com.example.netcracker.homework6.model.entity.Customer;
import com.example.netcracker.homework6.service.api.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Tag(name = "Customer")
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController extends EntityController<Customer, CustomerDTO> {

    private final CustomerService service;
    private final CustomerMapper mapper;

    public CustomerController(CustomerMapper mapper, CustomerService service) {
        super(mapper, service);
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Customer Homes (Unique); Task: 2.b")
    @GetMapping("homes")
    public Set<String> findCustomerHomes() {
        return service.findCustomerHomes();
    }

    @Operation(summary = "Get Customer surname and discount By it's home; Task: 3.a")
    @GetMapping("home/{name}")
    public List<CustomerDTO> getCustomerSurnameAndDiscountByHomes(@PathVariable String name) {
        return toDtos(service.findNameAndDiscountByHome(name));
    }

    @Override
    protected void setId(UUID id, CustomerDTO dto) {
        dto.setId(id);
    }
}
