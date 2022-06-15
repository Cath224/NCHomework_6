package com.example.netcracker.homework6.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;


@SqlResultSetMapping(
        name = "bookTitlePriceMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Book.class,
                        columns = {
                                @ColumnResult(name = "TITLE"),
                                @ColumnResult(name = "PRICE")
                        }
                )
        }
)
@NamedNativeQuery(name = "Book.findAllGroupByTitleAndPrice",
        query = "SELECT TITLE as title, PRICE as price FROM BOOK GROUP BY TITLE, PRICE",
        resultSetMapping = "bookTitlePriceMapping"
)
@NamedNativeQuery(name = "Book.findAllByNameTitleLikeOrPriceMoreSortByNameAndPriceDesc",
        query = "SELECT TITLE, PRICE FROM BOOK WHERE TITLE ~ CONCAT('^.*((\\s', :title, ')|(', :title, '\\s)|(\\s', :title, '\\s)).*$') OR PRICE > :price ORDER BY TITLE, PRICE DESC ",
        resultSetMapping = "bookTitlePriceMapping"
)
@Entity(name = "book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private String stock;

    @Column(name = "quantity")
    private BigInteger quantity;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Purchase> purchases;

    public Book() {
    }

    public Book(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
