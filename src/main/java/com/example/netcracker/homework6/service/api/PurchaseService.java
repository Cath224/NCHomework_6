package com.example.netcracker.homework6.service.api;

import com.example.netcracker.homework6.model.entity.Purchase;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

public interface PurchaseService extends EntityService<Purchase> {

    Set<String> findMonths();
    List<Purchase> findAllByTotalPriceGreaterThanEqual(BigDecimal price);
    List<Purchase> findPurchasesInCustomerAreaCreatedTimestampGreaterThen(String customerSurname, OffsetDateTime date);
    List<Purchase> findAllPurchasesInCustomerAreaCreatedTimestampGreaterThen(OffsetDateTime date);

}
