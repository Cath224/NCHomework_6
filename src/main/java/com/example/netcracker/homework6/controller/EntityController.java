package com.example.netcracker.homework6.controller;

import com.example.netcracker.homework6.mapper.EntityMapper;
import com.example.netcracker.homework6.service.api.EntityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public abstract class EntityController<E, D> {

    private final EntityMapper<E, D> mapper;
    private final EntityService<E> service;

    public EntityController(EntityMapper<E, D> mapper, EntityService<E> service) {
        this.mapper = mapper;
        this.service = service;
    }


    @Operation(summary = "Create Entity")
    @PostMapping
    public D create(@RequestBody D dto) {
        return mapper.toDto(service.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Full update Entity by id")
    @PutMapping("{id}")
    public D update(@PathVariable UUID id, @RequestBody D dto) {
        setId(id, dto);
        return mapper.toDto(service.update(mapper.toEntity(dto)));
    }

    @Operation(summary = "Partial update Entity by id")
    @PatchMapping("{id}")
    public D partialUpdate(@PathVariable UUID id, @RequestBody Map<String, Object> dto) {
        dto.put("id", id);
        return mapper.toDto(service.partialUpdate(dto));
    }

    // Yeah
    @Operation(summary = "Delete Everything, HA HA HA HA HA")
    @DeleteMapping
    public void deleteAll() {
        service.delete();
    }

    @Operation(summary = "Delete Entity By Id")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }

    @Operation(summary = "Get All Entities")
    @GetMapping
    public List<D> get() {
        return toDtos(service.get());
    }

    @Operation(summary = "Get Entity By Id")
    @GetMapping("{id}")
    public D getById(@PathVariable UUID id) {
        return mapper.toDto(service.getById(id));
    }


    protected abstract void setId(UUID id, D dto);


    protected List<E> toEntities(List<D> dtos) {
        if (dtos == null) {
            return null;
        }
        if (dtos.isEmpty()) {
            return Collections.emptyList();
        }
        List<E> entities = new ArrayList<>(dtos.size());
        for (D dto : dtos) {
            entities.add(mapper.toEntity(dto));
        }
        return entities;
    }

    protected List<D> toDtos(List<E> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<D> dtos = new ArrayList<>(entities.size());
        for (E entity : entities) {
            dtos.add(mapper.toDto(entity));
        }
        return dtos;
    }
}
