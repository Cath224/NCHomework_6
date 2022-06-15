package com.example.netcracker.homework6.repository;

import com.example.netcracker.homework6.model.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


@Repository
public interface BookRepository extends EntityRepository<Book> {

    List<Book> findAllGroupByTitleAndPrice();


    List<Book> findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc(@Param("title") String title, @Param("price") BigDecimal price);

    @Query("select b from book b " +
            "left join purchase p on p.book.id = b.id " +
            "left join shop s on s.id = p.shop.id " +
            "where s.district = b.stock and b.quantity > :quantity " +
            "order by b.quantity")
    List<Book> findAllBySameStockAndQuantity(@Param("quantity") BigInteger quantity);
}

