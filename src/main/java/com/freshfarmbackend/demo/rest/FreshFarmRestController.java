package com.freshfarmbackend.demo.rest;
import com.freshfarmbackend.demo.entity.*;
import com.freshfarmbackend.demo.service.appService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FreshFarmRestController {
    private appService appService;
    public FreshFarmRestController(appService theAppService) {
        this.appService = theAppService;
    }
    @GetMapping("/customers")
    public List<Customers> findAllCustomer(){
        List<Customers> customer=appService.findAll();
        System.out.println(customer.get(0).getOrders().get(0).getFarmers());
        System.out.println(customer);
        return customer;

//        return customers;
    }
    // working perfect...
    @GetMapping("/customerId/{email}")
    public Customers getcustomerID(@PathVariable String email){
         return appService.getcustomerId(email);
    }
    // working perfect..
    @GetMapping("/farmerId/{email}")
    public Farmers getfarmerID(@PathVariable String email){
        return appService.getfarmerId(email);
    }
    // show all products to the customers - kaam kr rha h y bhi link
    @GetMapping("/getProduct")
    public List<Product> getAllProduct(){
        List<Product> getProducts = appService.getAllProducts();
        System.out.println(getProducts);
        return getProducts;
    }
    // for placing the order.. -- kaam kr rha h y bhi
    @PostMapping("/orders/{cid}/{fid}/{pid}")
    public Orders addOrder(@RequestBody Orders orders,@PathVariable int cid,@PathVariable int fid,@PathVariable int pid){
        return  appService.addOrder(orders,cid,fid,pid);

    }
    // for cancelling the order... kaam kr rha h..
    @PutMapping("/orders/{id}")
    public Orders updateOrder(@RequestBody Orders orders,@PathVariable int id){
        return  appService.updateOrderStatus(orders,id);

    }
    // search krne k liye y api h
    @GetMapping("/getProduct/{name}")
    public List<Product> searchProduct(@PathVariable String name){
        List<Product> getProducts = appService.searchProducts(name);
//        System.out.println(getProducts.get(0).getFarmer());
        return getProducts;
    }
    // get products by farmer id -kaam kr rha h
    @GetMapping("/getProductByFarmerId/{identifyingId}")
    public List<Product> getProductsByFarmerId(@PathVariable int identifyingId){
        List<Product> getProducts = appService.getProductByFarmerId(identifyingId);
        return getProducts;
    }
    // save/update the product in the database - post ho ja rha h easily
    @PostMapping("/product/{id}")
    public Product addProduct(@RequestBody Product theProduct,@PathVariable int id){
        //also just in case they pass an in json ... set id to 0
        //this is to force a save of new item .. instead of update
//        theProduct.setId(0);
        Product dbProduct = appService.save(theProduct,id);
        return  dbProduct;
    }
    // update the products -- y bhi sahi se kaam kr rha h..
    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product theProduct,@PathVariable int id){
        Product dbProduct = appService.update(theProduct,id);
        return dbProduct;
    }
    // order for the customer. -- kaam kr rha h y bhi..
    @GetMapping("/orderByCustomerMail/{identifyingMail}")
    public List<Orders> getOrderByCustomerMail(@PathVariable String identifyingMail){
        List<Orders> getOrderCustomer=appService.getOrderCustomer(identifyingMail);
        return getOrderCustomer;
    }
    // order for the customer. --kaam kr rha h
    @GetMapping("/orderByFarmerMail/{identifyingMail}")
    public List<Orders> getOrderByFarmerMail(@PathVariable String identifyingMail){
        List<Orders> getOrderCustomer=appService.getOrderFarmer(identifyingMail);
        return getOrderCustomer;
    }
    // get cart items -- kaam kr rha h
    @GetMapping("/cart/{id}")
    public List<Cart> getCartItem(@PathVariable int id){
        List<Cart> carts=appService.getCartItem(id);
        return carts;
    }
    // add items in the cart --kaam kr rha h
    @PostMapping("/cart/{id}/{pid}")
    public Cart addCartItem(@PathVariable int id,@RequestBody Cart theCart,@PathVariable int pid){
        Cart cart  = appService.addCartItem(theCart,id,pid);
        return  cart;

    }
    // remove items from the cart --kaam kr rha h
  @DeleteMapping("/cart/{id}")
    public void deleteCartItem(@PathVariable int id){
        appService.deleteCartItem(id);
  }
  // check user is present or not - sahi h y..
    @GetMapping("/customer/{email}")
    public boolean userExist(@PathVariable String email){
        return appService.userExist(email);
    }
    // add the user into the database.. - sahi h y bhi
    @PostMapping("/customerDetails")
    public Customers addCustomer(@RequestBody Customers customers){
        return  appService.addCustomer(customers);
    }
    // add the user registration details.. -- sahi h y bhi
    @PutMapping("/customerDetails/{email}")
    public Customers addCustomerDetails(@RequestBody Customers customers,@PathVariable String email){
        return appService.addCustomerDetails(customers,email);
    }
    // check the email and pancard -sahi h y bhi..
    @GetMapping("/farmerpp/{email}/{pancard}")
    public boolean checkMailPan(@PathVariable String email,@PathVariable String pancard){
        return appService.checkemailpan(email,pancard);
    }
    // update the password - sahi chal rha h..
    @PutMapping("/farmer/{email}")
    public Farmers updatePassword(@RequestBody String pass,@PathVariable String email){
        return appService.updatePassword(pass,email);
    }
    // sahi chal rha h y bhi...
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        appService.deleteProduct(id);
    }
    // sahi chal rha h y bhi..
    @PutMapping("/order/{id}")
    public void deleteOrder(@PathVariable int id){
        appService.deleteOrder(id);
    }
    // get farmerId by product -- sahi chal rha h y bhi..
    @GetMapping("/farmeridbyproduct/{id}")
    public int getFarmerIdbyProduct(@PathVariable int id){
        return appService.getFarmerIdbyProduct(id);
    }
    @GetMapping("/productbycart/{id}/{pid}")
    public int getProductbycart(@PathVariable int id){
        return appService.getProductbbycart(id);
    }
}
