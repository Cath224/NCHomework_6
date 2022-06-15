package com.example.netcracker.homework6.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseExtendedDataDTO {

    private OffsetDateTime createdTimestamp;
    private String customerSurname;
    private float customerDiscount;
    private PurchaseExtendedDataBookDTO book;


    public OffsetDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(OffsetDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public float getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(float customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public PurchaseExtendedDataBookDTO getBook() {
        return book;
    }

    public void setBook(PurchaseExtendedDataBookDTO book) {
        this.book = book;
    }
}
