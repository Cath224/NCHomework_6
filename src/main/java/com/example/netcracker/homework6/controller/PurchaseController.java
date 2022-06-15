package com.example.netcracker.homework6.controller;

import com.example.netcracker.homework6.mapper.PurchaseMapper;
import com.example.netcracker.homework6.model.dto.*;
import com.example.netcracker.homework6.model.entity.Book;
import com.example.netcracker.homework6.model.entity.Customer;
import com.example.netcracker.homework6.model.entity.Purchase;
import com.example.netcracker.homework6.service.api.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Purchase")
@RestController
@RequestMapping("api/v1/purchases")
public class PurchaseController extends EntityController<Purchase, PurchaseDTO> {

    private final PurchaseService service;
    private final PurchaseMapper mapper;

    public PurchaseController(PurchaseMapper mapper, PurchaseService service) {
        super(mapper, service);
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Purchases month (Unique); Task: 2.c")
    @GetMapping("months")
    public Set<String> findMonths() {
        return service.findMonths();
    }

    @Operation(summary = "Get Customer Surname and Shop By Purchase Id; Task: 4.a")
    @GetMapping("{id}/customerShop")
    public PurchaseCustomerShopDTO getCustomerShopDataById(@PathVariable UUID id) {
        Purchase purchase = service.getById(id);
        PurchaseCustomerShopDTO purchaseCustomerShopDto = new PurchaseCustomerShopDTO();
        purchaseCustomerShopDto.setCustomerSurname(purchase.getCustomer().getSurname());
        purchaseCustomerShopDto.setShopName(purchase.getShop().getName());
        return purchaseCustomerShopDto;
    }

    @Operation(summary = "Get All Customer Surname and Shop By Purchases; Task: 4.a")
    @GetMapping("customerShop")
    public List<PurchaseCustomerShopDTO> getCustomerShopData() {
        return service.get().stream()
                .map((el) -> {
                    PurchaseCustomerShopDTO purchaseCustomerShopDto = new PurchaseCustomerShopDTO();
                    purchaseCustomerShopDto.setCustomerSurname(el.getCustomer().getSurname());
                    purchaseCustomerShopDto.setShopName(el.getShop().getName());
                    return purchaseCustomerShopDto;
                })
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get Purchase Date, Customer Surname, Customer Discount, Book Title and Book Quantity By Purchase Id; Task: 4.b")
    @GetMapping("{id}/extended")
    public PurchaseExtendedDataDTO getExtendedDataById(@PathVariable UUID id) {
        Purchase purchase = service.getById(id);
        PurchaseExtendedDataDTO extendedDataDto = new PurchaseExtendedDataDTO();
        extendedDataDto.setCreatedTimestamp(purchase.getCreatedTimestamp());
        Customer customer = purchase.getCustomer();
        extendedDataDto.setCustomerSurname(customer.getSurname());
        extendedDataDto.setCustomerDiscount(customer.getDiscount());
        Book book = purchase.getBook();
        PurchaseExtendedDataBookDTO bookDto = new PurchaseExtendedDataBookDTO();
        book.setQuantity(purchase.getQuantity());
        book.setTitle(book.getTitle());
        extendedDataDto.setBook(bookDto);
        return extendedDataDto;
    }

    @Operation(summary = "Get All Purchase Date, Customer Surname, Customer Discount, Book Title and Book Quantity; Task: 4.b")
    @GetMapping("extended")
    public List<PurchaseExtendedDataDTO> getExtendedData() {
        return service.get().stream()
                .map((el) -> {
                    PurchaseExtendedDataDTO extendedDataDto = new PurchaseExtendedDataDTO();
                    extendedDataDto.setCreatedTimestamp(el.getCreatedTimestamp());
                    Customer customer = el.getCustomer();
                    extendedDataDto.setCustomerSurname(customer.getSurname());
                    extendedDataDto.setCustomerDiscount(customer.getDiscount());
                    Book book = el.getBook();
                    PurchaseExtendedDataBookDTO bookDto = new PurchaseExtendedDataBookDTO();
                    bookDto.setQuantity(book.getQuantity());
                    bookDto.setTitle(book.getTitle());
                    extendedDataDto.setBook(bookDto);
                    return extendedDataDto;
                })
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get Purchase, Customer Data by total price greater then ;Task: 5.a")
    @GetMapping("extended/price")
    public List<PurchaseCustomerExtendedPriceDTO> findAllByTotalPriceGreaterThanEqual(@RequestParam BigDecimal price) {
        return service.findAllByTotalPriceGreaterThanEqual(price).stream()
                .map((el) -> {
                    PurchaseCustomerExtendedPriceDTO dto = new PurchaseCustomerExtendedPriceDTO();
                    dto.setId(el.getId());
                    dto.setCreatedTimestamp(el.getCreatedTimestamp());
                    dto.setNumber(el.getNumber());
                    dto.setCustomerSurname(el.getCustomer().getSurname());
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Operation(summary = "Find Customer Purchases and it's data by month equals or greater then month and customer surname; Task: 5.b")
    @GetMapping("customer-home")
    public List<PurchaseCustomerInHisAreaDTO> findCustomerPurchaseInfoInfoInHisArea(@RequestParam Integer month,
                                                                                    @RequestParam(required = false) String surname) {
        OffsetDateTime dateTime = OffsetDateTime.now().withMonth(month);
        List<Purchase> result;
        if (surname == null) {
            result = service.findAllPurchasesInCustomerAreaCreatedTimestampGreaterThen(dateTime);
        } else {
            result = service.findPurchasesInCustomerAreaCreatedTimestampGreaterThen(surname, dateTime);
        }
        return result.stream()
                .map(mapper::toCustomerAreaDto)
                .collect(Collectors.toList());
    }

    @Override
    protected void setId(UUID id, PurchaseDTO dto) {
        dto.setId(id);
    }
}
