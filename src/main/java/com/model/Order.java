package com.model;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private double price;
    private int productCount;
    private String customerName;
    private int customerDiscount;

    //Constructor with id
    public Order(int orderId, LocalDate createdAt, LocalDate updatedAt, double price, int productCount) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.price = price;
        this.productCount = productCount;
    }

    //Constructor without id
    public Order(LocalDate createdAt, LocalDate updatedAt, double price, int productCount) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.price = price;
        this.productCount = productCount;
    }

    //Constructor with data from many tables
    public Order(
            int orderId, LocalDate createdAt, LocalDate updatedAt, double price, int productCount,
            String customerName, int customerDiscount
    ) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.price = price;
        this.productCount = productCount;
        this.customerName = customerName;
        this.customerDiscount = customerDiscount;
    }

    //Getters & Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(int customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdateAt() {
        return updatedAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}

