package com.example.netcracker.homework6.mapper;

import com.example.netcracker.homework6.model.dto.PurchaseCustomerInHisAreaDTO;
import com.example.netcracker.homework6.model.dto.PurchaseDTO;
import com.example.netcracker.homework6.model.entity.Book;
import com.example.netcracker.homework6.model.entity.Customer;
import com.example.netcracker.homework6.model.entity.Purchase;
import com.example.netcracker.homework6.model.entity.Shop;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = EntityMapper.class)
public interface PurchaseMapper extends EntityMapper<Purchase, PurchaseDTO> {

    @Override
    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "customerId", target = "customer.id")
    Purchase toEntity(PurchaseDTO dto);

    @Override
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "customer.id", target = "customerId")
    PurchaseDTO toDto(Purchase entity);


    @Mapping(source = "customer.surname", target = "customerSurname")
    @Mapping(source = "customer.home", target = "customerArea")
    @Mapping(source = "createdTimestamp", target = "purchaseTimestamp")
    PurchaseCustomerInHisAreaDTO toCustomerAreaDto(Purchase purchase);

    @AfterMapping
    default void afterMapping(@MappingTarget Purchase purchase) {
        if (purchase == null) {
            return;
        }
        Shop shop = purchase.getShop();
        if (shop == null || shop.getId() == null) {
            purchase.setShop(null);
        }

        Customer customer = purchase.getCustomer();
        if (customer == null || customer.getId() == null) {
            purchase.setCustomer(null);
        }

        Book book = purchase.getBook();
        if (book == null || book.getId() == null) {
            purchase.setBook(null);
        }
    }

}
