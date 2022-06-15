package com.example.netcracker.homework6.repository;

import com.example.netcracker.homework6.model.entity.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ShopRepository extends EntityRepository<Shop> {

    @Query(value = "SELECT NAME FROM SHOP WHERE DISTRICT in :districts", nativeQuery = true)
    Set<String> findNamesByDistricts(@Param("districts") Set<String> districts);


    @Query(value = "select s from shop s " +
            "left join purchase p on s.id = p.shop.id " +
            "left join customer c on p.customer.id = c.id " +
            "where s.district <> :district and c.discount between :minDiscount and :maxDiscount")
    List<Shop> findAllByDistrictNotAndPurchasesCustomersWithDiscountRange(@Param("district") String district,
                                                                          @Param("minDiscount") float minDiscount,
                                                                          @Param("maxDiscount") float maxDiscount);
}

