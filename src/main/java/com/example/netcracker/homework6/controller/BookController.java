package com.example.netcracker.homework6.controller;

import com.example.netcracker.homework6.mapper.BookMapper;
import com.example.netcracker.homework6.model.dto.BookDTO;
import com.example.netcracker.homework6.model.entity.Book;
import com.example.netcracker.homework6.service.api.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Tag(name = "Book")
@RestController
@RequestMapping("api/v1/books")
public class BookController extends EntityController<Book, BookDTO> {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookMapper mapper, BookService service) {
        super(mapper, service);
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Book's Title and Price grouping by ones; Task 2.a")
    @GetMapping("namePrice")
    public List<BookDTO> getByGroupByTitleAndPrice() {
        return toDtos(service.getByGroupByTitleAndPrice());
    }

    @Operation(summary = "Get Books By Title contains word or price greater then, sorted; Task 3.c")
    @GetMapping("namePrice/params")
    public List<BookDTO> findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(@RequestParam String title, @RequestParam BigDecimal price) {
        return toDtos(service.findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(title, price));
    }

    @Operation(summary = "Get Books sold in the their stock area with quantity more then parameter(default 10), sorted by price asc; Task 5.d")
    @GetMapping("filter-same-stock-area")
    public List<BookDTO> findAllBySameStockAndQuantity(@RequestParam(required = false, defaultValue = "10") BigInteger quantity) {
        return toDtos(service.findAllBySameStockAndQuantity(quantity));
    }

    @Override
    protected void setId(UUID id, BookDTO dto) {
        dto.setId(id);
    }
}
