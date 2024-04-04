package com.freshfarmbackend.demo.service;

import com.freshfarmbackend.demo.entity.*;

import java.util.List;

public interface appService {
    List<Customers> findAll();
    List<Product> getAllProducts();

    List<Product> getProductByFarmerId(int identifyingId);

    List<Orders> getOrderCustomer(String identifyingMail);

    List<Orders> getOrderFarmer(String identifyingMail);

    Product save(Product theProduct,int id);

    Product update(Product theProduct,int id);

    List<Cart> getCartItem(int id);

    Cart addCartItem(Cart theCart, int id,int pid);

    void deleteCartItem(int id);

    List<Product> searchProducts(String name);

    boolean userExist(String email);

    Customers addCustomer(Customers customers);

    Customers addCustomerDetails(Customers customers, String email);

    Customers getcustomerId(String email);

    Farmers getfarmerId(String email);

    Orders addOrder(Orders orders, int cid, int fid);
    public Orders updateOrderStatus(Orders orders,int id);

    boolean checkemailpan(String email, String pancard);

    Farmers updatePassword(String pass,String email);

    void deleteProduct(int id);

    void deleteOrder(int id);

    int getFarmerIdbyProduct(int id);

    int getProductbbycart(int id);
}
