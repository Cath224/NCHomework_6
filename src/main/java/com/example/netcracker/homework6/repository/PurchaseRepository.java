package com.example.netcracker.homework6.repository;

import com.example.netcracker.homework6.model.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface PurchaseRepository extends EntityRepository<Purchase> {

    @Query(value = "SELECT DISTINCT TRIM(TO_CHAR(created_timestamp, 'Month')) FROM purchase", nativeQuery = true)
    Set<String> findMonths();


    List<Purchase> findAllByTotalPriceGreaterThanEqual(@Param("price") BigDecimal price);


    @Query("select p, p.customer from purchase p left join customer c on c.id = p.customer.id " +
            "left join shop s on s.id = p.shop.id and s.district = c.home " +
            "where c.surname = :customerSurname and p.createdTimestamp >= :date order by p.createdTimestamp")
    List<Purchase> findPurchasesInCustomerAreaCreatedTimestampGreaterThen(@Param("customerSurname") String customerSurname,
                                                                          @Param("date") OffsetDateTime date);

    @Query("select  p, p.customer from purchase p left join customer c on c.id = p.customer.id " +
            "left join shop s on s.id = p.shop.id and s.district = c.home " +
            "where p.createdTimestamp >= :date order by p.createdTimestamp")
    List<Purchase> findAllPurchasesInCustomerAreaCreatedTimestampGreaterThen(@Param("date") OffsetDateTime date);

}
