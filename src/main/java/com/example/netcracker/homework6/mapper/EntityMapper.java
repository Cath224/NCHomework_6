package com.example.netcracker.homework6.mapper;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface EntityMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}
