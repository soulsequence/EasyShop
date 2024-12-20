package org.yearup.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartID implements Serializable {
    private long userId; // Cannot be null.
    private long productId; // Cannot be null.
    public ShoppingCartID(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
