package com.example.netcracker.homework6.service.impl;

import com.example.netcracker.homework6.model.entity.Purchase;
import com.example.netcracker.homework6.repository.PurchaseRepository;
import com.example.netcracker.homework6.service.api.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class PurchaseServiceImpl extends EntityServiceImpl<Purchase> implements PurchaseService {

    private static final String TABLE_NAME = Purchase.class.getAnnotation(Entity.class).name();
    private static final Set<String> FIELDS = Arrays.stream(Purchase.class.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    private final PurchaseRepository repository;

    public PurchaseServiceImpl(PurchaseRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected UUID getEntityId(Purchase entity) {
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
    public Set<String> findMonths() {
        return repository.findMonths();
    }

    @Override
    public List<Purchase> findAllByTotalPriceGreaterThanEqual(BigDecimal price) {
        return repository.findAllByTotalPriceGreaterThanEqual(price);
    }

    @Override
    public List<Purchase> findPurchasesInCustomerAreaCreatedTimestampGreaterThen(String customerSurname, OffsetDateTime date) {
        return repository.findPurchasesInCustomerAreaCreatedTimestampGreaterThen(customerSurname, date);
    }

    @Override
    public List<Purchase> findAllPurchasesInCustomerAreaCreatedTimestampGreaterThen(OffsetDateTime date) {
        return repository.findAllPurchasesInCustomerAreaCreatedTimestampGreaterThen(date);
    }
}
