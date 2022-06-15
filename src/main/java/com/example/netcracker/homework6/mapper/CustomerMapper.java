package com.example.netcracker.homework6.mapper;

import com.example.netcracker.homework6.model.dto.CustomerDTO;
import com.example.netcracker.homework6.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapper.class)
public interface CustomerMapper extends EntityMapper<Customer, CustomerDTO> {
}
