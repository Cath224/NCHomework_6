package com.example.netcracker.homework6.service.impl;

import com.example.netcracker.homework6.repository.EntityRepository;
import com.example.netcracker.homework6.service.api.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

public abstract class EntityServiceImpl<E> implements EntityService<E> {

    private static final String UPDATE_SQL_QUERY = "UPDATE %s SET %s WHERE id = '%s'";

    private static final String UPDATE_SQL_QUERY_SET = "%s = :%s";
    private final EntityRepository<E> repository;
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityServiceImpl(EntityRepository<E> repository) {
        this.repository = repository;
    }

    @Override
    public E create(E entity) {
        E result = repository.save(entity);
        repository.flush();
        return result;
    }

    @Override
    public E update(E entity) {
        UUID id = getEntityId(entity);
        getById(id);
        E result = repository.save(entity);
        repository.flush();
        return result;
    }


    @Transactional
    @Override
    public E partialUpdate(Map<String, Object> fields) {
        if (fields == null || fields.isEmpty()) {
            throw new RuntimeException();
        }
        UUID id = (UUID) fields.get("id");
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no Id to update");
        }
        getById(id);
        fields.remove("id");
        validateFields(fields.keySet());
        List<String> sets = new ArrayList<>();
        for (String field : fields.keySet()) {
            sets.add(String.format(UPDATE_SQL_QUERY_SET, camelCaseToSnake(field), field));
        }
        String querySql = String.format(UPDATE_SQL_QUERY, getTableName(), String.join(", ", sets), id);
        Query query = entityManager.createNativeQuery(querySql);
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.executeUpdate();
        entityManager.clear();
        repository.flush();
        return getById(id);
    }

    @Override
    public void delete() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public E getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<E> get() {
        return repository.findAll();
    }

    protected abstract UUID getEntityId(E entity);

    protected abstract Set<String> getFields();

    protected abstract String getTableName();

    protected String camelCaseToSnake(String camelCaseString) {
        String result = camelCaseString.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2");
        return result.toLowerCase();
    }

    private void validateFields(Set<String> fields) {
        Set<String> entityFields = getFields();
        for (String field : fields) {
            if (!entityFields.contains(field)) {
                throw new RuntimeException();
            }
        }
    }

}
