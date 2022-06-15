package com.example.netcracker.homework6.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@SqlResultSetMapping(
        name = "customerSurnameDiscountMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Customer.class,
                        columns = {
                                @ColumnResult(name = "SURNAME"),
                                @ColumnResult(name = "DISCOUNT")
                        }
                )
        }
)
@NamedNativeQuery(name = "Customer.findNameAndDiscountByHome",
        query = "SELECT SURNAME, DISCOUNT FROM CUSTOMER WHERE HOME = ?",
        resultSetMapping = "customerSurnameDiscountMapping"
)
@Entity(name = "customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "home")
    private String home;

    @Column(name = "discount")
    private float discount;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;


    public Customer() {
    }

    public Customer(String surname, float discount) {
        this.surname = surname;
        this.discount = discount;
    }

    public Customer(String surname, String home) {
        this.surname = surname;
        this.home = home;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}

