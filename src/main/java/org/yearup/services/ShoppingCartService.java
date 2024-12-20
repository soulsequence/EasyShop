package org.yearup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.data.ShoppingCartRepository;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart == null) {
            return new ShoppingCart();
        }
        return shoppingCart;
    }

    public ShoppingCart saveProductToCart(int userId, int productId) {
        ShoppingCart cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new ShoppingCart();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setUserId(1);
            cartItem.setQuantity(1);
        }
        return cartItem;
    }
}
