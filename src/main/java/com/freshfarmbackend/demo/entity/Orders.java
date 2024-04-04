package com.freshfarmbackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private String status;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private double amount;
    // join the tables here
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonBackReference(value = "customers-order")
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    @JsonBackReference(value = "farmers-order")
    private Farmers farmers;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "user-order")
    private Product product;

    public Orders() {
    }

    public Orders(String status, int quantity, double amount) {
        this.status = status;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Farmers getFarmers() {
        return farmers;
    }

    public void setFarmers(Farmers farmers) {
        this.farmers = farmers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", productId="+ product.getId() +
                ", customerId="+ product.getId() +
                ", farmerId="+ product.getId() +
                '}';
    }

}
