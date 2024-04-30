package com.freshfarmbackend.demo.dao;

import com.freshfarmbackend.demo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class appDAOImpl implements appDAO{
    EntityManager entityManager;
    @Autowired
    public appDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customers> findAll() {
        TypedQuery<Customers> theQuery=entityManager.createQuery("from Customers",Customers.class);
        List<Customers> theCustomers=theQuery.getResultList();
        return  theCustomers;
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> theQuery=entityManager.createQuery("from Product",Product.class);
        List<Product> theProducts=theQuery.getResultList();
        return theProducts;
    }

    @Override
    public List<Product> getProductByFarmerId(int id) {
        Farmers farmers =entityManager.find(Farmers.class,id);
        List<Product> theProducts=farmers.getProducts();
        return  theProducts;
    }

    @Override
    public List<Orders> getOrderCustomer(String identifyingMail) {
        TypedQuery<Customers> theQuery=entityManager.createQuery("from Customers c where c.email= :identifyingMail",Customers.class).setParameter("identifyingMail",identifyingMail);
        Customers customers = theQuery.getSingleResult();
        List<Orders> theOrders=customers.getOrders();
        return  theOrders;
    }

    @Override
    public List<Orders> getFarmerOrder(String identifyingMail) {
        TypedQuery<Farmers> theQuery=entityManager.createQuery("from Farmers f where f.email= :identifyingMail",Farmers.class).setParameter("identifyingMail",identifyingMail);
        Farmers farmers = theQuery.getSingleResult();
        List<Orders> theOrders=farmers.getOrders();
        return  theOrders;
    }

    @Override
    public Product save(Product theProduct, int id) {
        Farmers farmers=entityManager.find(Farmers.class,id);
//        System.out.println(farmers);
        farmers.addProduct(theProduct);
        entityManager.persist(theProduct);
        return theProduct;
    }

    @Override
    public Product update(Product theProduct,int id) {
        Product product=entityManager.find(Product.class,theProduct.getId());
        System.out.println(product);
        Farmers farmers=entityManager.find(Farmers.class,id);
//        product.setFarmer(farmers);
        product.setPrice(theProduct.getPrice());
        product.setQuantity(theProduct.getQuantity());
        List<Product> products =farmers.getProducts();
        for (int i=0;i<products.size();i++) {
            if (products.get(i).getId() == product.getId()) {
                products.get(i).setQuantity(product.getQuantity());
                products.get(i).setPrice(product.getPrice());
            }
        }
//        farmers.setProducts(products);
//        farmers.addProduct(product);
        entityManager.merge(farmers);
        return  theProduct;
    }

    @Override
    public List<Cart> getCartItem(int id) {
        Customers customers=entityManager.find(Customers.class,id);
        return  customers.getCarts();
    }

    @Override
    public Cart addCartItem(Cart cart, int id, int pid) {
        Customers customers=entityManager.find(Customers.class,id);
        customers.addCart(cart);
        Product product=entityManager.find(Product.class,pid);
        product.addCart(cart);
        entityManager.persist(cart);
        return cart;
    }

    @Override
    public void deleteCartItem(int id) {
        TypedQuery<Cart> theQuery=entityManager.createQuery("from Cart c where c.id= :id",Cart.class).setParameter("id",id);
        Cart c = theQuery.getSingleResult();
        System.out.println(c);
        entityManager.remove(c);
        System.out.println("Done!");
    }

    @Override
    public List<Product> searchProduct(String name) {
        TypedQuery<Product> theQuery = entityManager.createQuery("from Product p where p.name= :name",Product.class).setParameter("name",name);
        return theQuery.getResultList();
    }

    @Override
    public boolean userExist(String email) {

        TypedQuery<Customers> theQuery = entityManager.createQuery("from Customers c where c.email= :email",Customers.class).setParameter("email",email);
        Customers customers;
        try{
            customers=theQuery.getSingleResult();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Customers addCustomer(Customers customers) {
        entityManager.persist(customers);
        return  customers;
    }

    @Override
    public Customers addCustomerDetails(Customers customers, String email) {
        TypedQuery<Customers> theQuery=entityManager.createQuery("from Customers c where c.email= :email",Customers.class).setParameter("email",email);
        Customers customers1=theQuery.getSingleResult();
        customers1.setAddress(customers.getAddress());
        customers1.setCity(customers.getCity());
        customers1.setMobile(customers.getMobile());
        customers1.setPincode(customers.getPincode());
        customers1.setState(customers.getState());
        entityManager.merge(customers1);
        return customers1;
    }

    @Override
    public Customers getCustomerId(String email) {
        return entityManager.createQuery("from Customers c where c.email= :email",Customers.class).setParameter("email",email).getSingleResult();
    }



//    @Override
//    public Farmers getfarmerId(String email) {
//        Farmers farmers;
//        return farmers;
////        return entityManager.createQuery("from Farmers c where c.email= :email",Farmers.class).setParameter("email",email).getSingleResult();
//    }

    @Override
    public Orders addOrder(Orders orders, int cid, int fid, int pid) {
        Farmers farmers=entityManager.find(Farmers.class,fid);
        Customers customers=entityManager.find(Customers.class,cid);
        Product product=entityManager.find(Product.class,pid);
        product.addOrder(orders);
        farmers.addOrder(orders);
        customers.addOrder(orders);
        entityManager.persist(orders);
        entityManager.persist(farmers);
        entityManager.persist(product);
        return orders;
    }

    @Override
    public Orders updateOrderStatus(Orders orders,int id) {
        Orders order=entityManager.find(Orders.class,id);
        order.setStatus(orders.getStatus());
        entityManager.merge(order);
        return order;
    }

    @Override
    public boolean checkemailpan(String email, String pancard) {
//        TypedQuery<Farmers> theQuery = entityManager.createQuery("from Farmers f where f.pancard= :pancard",Farmers.class).setParameter("pancard",pancard);
        Farmers farmers;
        TypedQuery<Farmers> theQuery1 = entityManager.createQuery("from Farmers f"+" where f.email= :email"+" and f.pancard= :pancard",Farmers.class).setParameter("email",email).setParameter("pancard",pancard);
        try{
            farmers=theQuery1.getSingleResult();
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
    public Farmers getFarmer(String email) {
        return entityManager.createQuery("from Farmers c where c.email= :email",Farmers.class).setParameter("email",email).getSingleResult();
    }

    @Override
    public void deleteProduct(int id) {
        Product p = entityManager.find(Product.class,id);
        entityManager.remove(p);
    }

    @Override
    public void deleteOrder(int id) {
        Orders order= entityManager.find(Orders.class,id);
        order.setStatus("cancelled");
        entityManager.merge(order);
    }

    @Override
    public int getFarmerIdbyProduct(int id) {
        Product product=entityManager.find(Product.class,id);
        int farmerId=product.getFarmer().getId();
        return farmerId;
    }

    @Override
    public int getProductbbycart(int id) {
        Cart cart=entityManager.find(Cart.class,id);
        int pid=cart.getProduct().getId();
        return pid;
    }

    @Override
    public Farmers updatePassword(String pass,String email) {
        Farmers fid =getFarmer(email);
        int id= fid.getId();;
        Farmers farmers=entityManager.find(Farmers.class,id);
        farmers.setPassword(pass);
        entityManager.merge(farmers);
        return farmers;
    }
    @Override
    public boolean checkFarmerEmail(String email) {
        TypedQuery<Farmers> theQuery = entityManager.createQuery("from Farmers f where f.email= :email",Farmers.class).setParameter("email",email);
        Farmers farmers;
        try{
            farmers=theQuery.getSingleResult();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String checkFarmerCredentials(String email, String password) {
        TypedQuery<Farmers> theQuery = entityManager.createQuery("from Farmers f where f.email= :email"+" and f.password= :password",Farmers.class).setParameter("email",email).setParameter("password",password);
        Farmers farmers;
        try{
            farmers=theQuery.getSingleResult();
           return farmers.getStatus();

        }catch (Exception e){
            return "";
        }
//        return true;
    }

    @Override
    public void createFarmer(Farmers farmers) {
        entityManager.persist(farmers);
        System.out.println(farmers);
    }
    @Override
    public String getFarmerEmail(int id) {
        Farmers farmers=entityManager.find(Farmers.class,id);
        return farmers.getEmail();
    }

    @Override
    public String getFarmerMailByOrderId(int id) {
        Orders orders=entityManager.find(Orders.class,id);
        int fid=orders.getFarmers().getId();
        Farmers farmer=entityManager.find(Farmers.class,fid);
        return farmer.getEmail();
        
    }

    @Override
    public List<Product> getProductByCategory(String category){
        TypedQuery<Product> theQuery = entityManager.createQuery("from Product p where p.category= :category",Product.class).setParameter("category",category);
        return theQuery.getResultList();
    }


    @Override
    public String orderName(String email) {
        return null;
    }




}
