package com.freshfarmbackend.demo.service;

import com.freshfarmbackend.demo.dao.appDAO;
import com.freshfarmbackend.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class appServiceImpl implements appService{
    private  appDAO appDAO;
    @Autowired
    public appServiceImpl(appDAO appDAO) {
        this.appDAO = appDAO;
    }
    @Override
    public List<Customers> findAll() {
        return appDAO.findAll();
    }

    @Override
    public List<Product> getAllProducts() {
        return appDAO.getAllProducts();
    }

    @Override
    public List<Product> getProductByFarmerId(int identifyingId) {
        return appDAO.getProductByFarmerId(identifyingId);
    }

    @Override
    public List<Orders> getOrderCustomer(String identifyingMail) {
        return appDAO.getOrderCustomer(identifyingMail);
    }

    @Override
    public List<Orders> getOrderFarmer(String identifyingMail) {
        return appDAO.getFarmerOrder(identifyingMail);
    }

    @Override
    @Transactional
    public Product save(Product theProduct,int id) {
        return appDAO.save(theProduct,id);
    }

    @Override
    @Transactional
    public Product update(Product theProduct,int id) {
        return appDAO.update(theProduct,id);
    }

    @Override
    public List<Cart> getCartItem(int id) {
        return appDAO.getCartItem(id);
    }

    @Override
    @Transactional
    public Cart addCartItem(Cart theCart, int id,int pid) {
        return appDAO.addCartItem(theCart,id,pid);
    }

    @Override
    @Transactional
    public void deleteCartItem(int id) {
        appDAO.deleteCartItem(id);
    }

    @Override
    public List<Product> searchProducts(String name) {
        return appDAO.searchProduct(name);
    }

    @Override
    public boolean userExist(String email) {
        return appDAO.userExist(email);
    }

    @Override
    @Transactional
    public Customers addCustomer(Customers customers) {
        return appDAO.addCustomer(customers);
    }

    @Override
    @Transactional
    public Customers addCustomerDetails(Customers customers, String email) {
        return appDAO.addCustomerDetails(customers,email);
    }

    @Override
    public Customers getcustomerId(String email) {
        return appDAO.getCustomerId(email);
    }

    @Override
    public Farmers getfarmerId(String email) {
        return appDAO.getFarmer(email);
    }

//    @Override
//    public Farmers getfarmerId(String email) {
//        return appDAO.getfarmerId(email);
//    }

    @Override
    @Transactional
    public Orders addOrder(Orders orders, int cid, int fid) {
        return appDAO.addOrder(orders,cid,fid);
    }

    @Override
    @Transactional
    public Orders updateOrderStatus(Orders orders, int id) {
        return appDAO.updateOrderStatus(orders,id);
    }

    @Override
    public boolean checkemailpan(String email, String pancard) {
        return appDAO.checkemailpan(email,pancard);
    }

    @Override
    @Transactional
    public Farmers updatePassword(String pass,String email) {
        return appDAO.updatePassword(pass,email);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        appDAO.deleteProduct(id);
    }

    @Override
    @Transactional
    public void deleteOrder(int id) {
        appDAO.deleteOrder(id);
    }

    @Override
    public int getFarmerIdbyProduct(int id) {
        return appDAO.getFarmerIdbyProduct(id);
    }

    @Override
    public int getProductbbycart(int id) {
        return appDAO.getProductbbycart(id);
    }
//    @Override
//    public String orderName(String email){return appDAO.orderName(email)};
}
