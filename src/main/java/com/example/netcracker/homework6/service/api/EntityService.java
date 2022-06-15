package com.example.netcracker.homework6.service.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EntityService<E> {

    E create(E entity);
    E update(E entity);
    E partialUpdate(Map<String, Object> fields);
    void delete();
    void deleteById(UUID id);
    E getById(UUID id);
    List<E> get();
}
