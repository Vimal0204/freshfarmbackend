package com.freshfarmbackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private double amount;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-cart")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonBackReference(value = "user-cart")
    private Customers customers;

    public Cart() {
    }

    public Cart(String productName, int quantity, double amount) {
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
