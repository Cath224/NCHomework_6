package com.example.netcracker.homework6.service.impl;

import com.example.netcracker.homework6.model.entity.Shop;
import com.example.netcracker.homework6.repository.ShopRepository;
import com.example.netcracker.homework6.service.api.ShopService;
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
public class ShopServiceImpl extends EntityServiceImpl<Shop> implements ShopService {

    private static final String TABLE_NAME = Shop.class.getAnnotation(Entity.class).name();
    private static final Set<String> FIELDS = Arrays.stream(Shop.class.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    private final ShopRepository repository;

    public ShopServiceImpl(ShopRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected UUID getEntityId(Shop entity) {
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
    public Set<String> findNamesByDistricts(Set<String> districts) {
        return repository.findNamesByDistricts(districts);
    }

    @Override
    public List<Shop> findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(String district, float minDiscount, float maxDiscount) {
        return repository.findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(district, minDiscount, maxDiscount);
    }
}
