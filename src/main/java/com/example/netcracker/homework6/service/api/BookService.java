package com.example.netcracker.homework6.service.api;

import com.example.netcracker.homework6.model.entity.Book;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface BookService extends EntityService<Book> {

    List<Book> getByGroupByTitleAndPrice();
    List<Book> findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(String title, BigDecimal price);
    List<Book> findAllBySameStockAndQuantity(BigInteger quantity);
}
