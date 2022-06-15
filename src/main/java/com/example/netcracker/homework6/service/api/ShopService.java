package com.example.netcracker.homework6.service.api;

import com.example.netcracker.homework6.model.entity.Shop;

import java.util.List;
import java.util.Set;

public interface ShopService extends EntityService<Shop> {

    Set<String> findNamesByDistricts(Set<String> districts);
    List<Shop> findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(String district,
                                                                          float minDiscount,
                                                                          float maxDiscount);
}
