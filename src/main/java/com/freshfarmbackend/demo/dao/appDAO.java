package com.freshfarmbackend.demo.dao;

import com.freshfarmbackend.demo.entity.*;

import java.util.List;

public interface appDAO {
    List<Customers> findAll();
    List<Product> getAllProducts();

    List<Product> getProductByFarmerId(int id);

    List<Orders> getOrderCustomer(String identifyingMail);

    List<Orders> getFarmerOrder(String identifyingMail);


    Product save(Product theProduct, int id);



    Product update(Product theProduct,int id);

    List<Cart> getCartItem(int id);

    Cart addCartItem(Cart cart, int id, int pid);

    void deleteCartItem(int id);

    List<Product> searchProduct(String name);

    boolean userExist(String email);

    Customers addCustomer(Customers customers);

    Customers addCustomerDetails(Customers customers, String email);

    Customers getCustomerId(String email);



    Orders addOrder(Orders orders, int cid, int fid);
    Orders updateOrderStatus(Orders orders,int id);

    boolean checkemailpan(String email, String pancard);

    Farmers updatePassword(String pass,String email);

    String orderName(String email);

    Farmers getFarmer(String email);

    void deleteProduct(int id);

    void deleteOrder(int id);

    int getFarmerIdbyProduct(int id);

    int getProductbbycart(int id);
}
