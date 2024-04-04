package com.freshfarmbackend.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "farmers")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Farmers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "pwd")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private int pincode;
    @Column(name = "state")
    private String state;
    @Column(name = "pancard")
    private String pancard;
    @Column(name = "status")
    private String status;

    @OneToMany(cascade ={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,}
            ,fetch = FetchType.LAZY
    )
    @JoinColumn(name = "farmer_id")
    @JsonManagedReference(value = "farmer-product")
    private List<Product> products;
    @OneToMany(cascade =
            {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.LAZY
    )
    @JoinColumn(name = "farmer_id")
    @JsonManagedReference(value = "farmers-order")
    private List<Orders> orders;
    public void addOrder(Orders theOrder){
        if (orders == null) {
            orders=new ArrayList<>();
        }
        orders.add(theOrder);
    }
    public Farmers() {
    }

    public Farmers(String firstName, String lastName, String email, String password, String mobile, String address, String city, int pincode, String state, String pancard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.pancard = pancard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    public void addProduct(Product tempProduct){
        if (products == null) {
            products=new ArrayList<>();
        }
        products.add(tempProduct);
        tempProduct.setFarmer(this);
    }

    @Override
    public String toString() {
        return "Farmers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", pancard='" + pancard + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
