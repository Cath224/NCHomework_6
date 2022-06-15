package com.example.netcracker.homework6.service.impl;


import com.example.netcracker.homework6.model.entity.Book;
import com.example.netcracker.homework6.repository.BookRepository;
import com.example.netcracker.homework6.service.api.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class BookServiceImpl extends EntityServiceImpl<Book> implements BookService {

    private static final String TABLE_NAME = Book.class.getAnnotation(Entity.class).name();
    private static final Set<String> FIELDS = Arrays.stream(Book.class.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    protected UUID getEntityId(Book entity) {
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
    public List<Book> getByGroupByTitleAndPrice() {
        return repository.findAllGroupByTitleAndPrice();
    }

    @Override
    public List<Book> findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(String title, BigDecimal price) {
        return repository.findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(title, price);
    }

    @Override
    public List<Book> findAllBySameStockAndQuantity(BigInteger quantity) {
        return repository.findAllBySameStockAndQuantity(quantity);
    }
}
