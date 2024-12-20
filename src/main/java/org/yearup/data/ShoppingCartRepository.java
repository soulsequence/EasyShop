package org.yearup.data;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartID;

@Repository
public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, ShoppingCartID> {
    ShoppingCart findByUserIdAndProductId(long userId, long productId);
    ShoppingCart findByUserId(long userId);
}
