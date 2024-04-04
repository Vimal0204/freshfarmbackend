package com.freshfarmbackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Customers {
    // define the fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;// String krna hoga....
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private int pincode;
    @Column(name = "state")
    private String state;
    //define the constructor
    // join the tables
    // join customer with order

    @OneToMany(fetch = FetchType.LAZY,cascade =
            {CascadeType.DETACH,CascadeType.REFRESH},mappedBy = "customers"
    )
    @JsonManagedReference(value = "customers-order")
    private List<Orders> orders;
    public void addOrder(Orders theOrders){
        if (orders == null) {
            orders=new ArrayList<>();
        }
        orders.add(theOrders);
    }
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE},mappedBy = "customers")
    @JsonManagedReference(value = "user-cart")
    private List<Cart> carts;
    public Customers() {
    }

    public Customers(int id, String name, String email, String mobile, String address, String city, int pincode, String state,List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.orders=orders;
    }
    //define the getter and setter

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    public void addCart(Cart tempCart){
        if (carts == null) {
            carts=new ArrayList<>();
        }
        carts.add(tempCart);
        tempCart.setCustomers(this);
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
//define the toString method

    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                ", state='" + state + '\'' +
//                ", orders=" + orders +
                '}';
    }

    // annotate the fields
}
