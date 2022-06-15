package com.example.netcracker.homework6.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseCustomerInHisAreaDTO {

    private String customerSurname;
    private String customerArea;
    private OffsetDateTime purchaseTimestamp;


    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public OffsetDateTime getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public void setPurchaseTimestamp(OffsetDateTime purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }
}
