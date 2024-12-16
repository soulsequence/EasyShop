package org.yearup.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.ShoppingCartRepository;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
import java.util.List;

// convert this class to a REST controller
// only logged in users should have access to these actions

@RestController
@RequestMapping("/cart")
public class ShoppingCartController
{
    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private ShoppingCartRepository shoppingCartRepository;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao,
                                  ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
        this.shoppingCartRepository = shoppingCartRepository;
    }

//    @GetMapping
//    public List<ShoppingCart> getAll() {
//        return shoppingCartRepository.findAll();
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public ShoppingCart getCart(Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            return shoppingCartRepository.findByUserId(userId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

//    // each method in this controller requires a Principal object as a parameter
//    @GetMapping
//    public ShoppingCart getCart(Principal principal)
//    {
//        try
//        {
//            // get the currently logged in username
//            String userName = principal.getName();
//            // find database user by userId
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//            // use the shoppingcartDao to get all items in the cart and return the cart
//            return shoppingCartDao.getByUserId(userId);
//        }
//        catch(Exception e)
//        {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added
    @PreAuthorize("isAuthenticated()")
    @PostMapping("products/{productId}")
    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            ShoppingCart cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
            if(cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                cartItem = new ShoppingCart();
                cartItem.setUserId(userId);
                cartItem.setProductId(productId);
                cartItem.setUserId(1);
                cartItem.setQuantity(1);
            }

            return shoppingCartRepository.save(cartItem);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated


    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart

}
