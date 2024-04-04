package com.freshfarmbackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "imagelink")
    private String imagelink;

    @Column(name = "cart_id")
    @OneToMany(mappedBy = "product",cascade =
            {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.LAZY)
    @JsonManagedReference (value = "product-cart")
    private List<Cart> carts;
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonBackReference(value = "farmer-product")
    private Farmers farmer;
    @OneToMany(mappedBy = "product",cascade =
            {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "user-order")
    private List<Orders> orders;
    public void addOrder(Orders theOrder){
        if (orders == null) {
            orders=new ArrayList<>();
        }
        orders.add(theOrder);
    }
    public void addCart(Cart theCart){
        if (carts == null) {
            carts=new ArrayList<>();
        }
        carts.add(theCart);
    }
    public Product() {
    }

    public Product(String name, int quantity, double price, String imagelink) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imagelink = imagelink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public Farmers getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmers farmer) {
        this.farmer = farmer;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imagelink='" + imagelink + '\'' +
                '}';
    }
}
