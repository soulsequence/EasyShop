package org.yearup.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@IdClass(ShoppingCartID.class)
@Table(name = "shopping_cart")
public class ShoppingCart
{
    @Id
    private long userId;
    @Id
    private long productId;

    private int quantity;
//
//    private Map<Integer, ShoppingCartItem> items = new HashMap<>();
//
//    public Map<Integer, ShoppingCartItem> getItems()
//    {
//        return items;
//    }
//
//    public void setItems(Map<Integer, ShoppingCartItem> items)
//    {
//        this.items = items;
//    }
//
//    public boolean containsItem(int productId)
//    {
//        return items.containsKey(productId);
//    }
//
//    public void addItem(ShoppingCartItem item)
//    {
//        items.put(item.getProductId(), item);
//    }
//
//    public ShoppingCartItem getItem(int productId)
//    {
//        return items.get(productId);
//    }

//    public BigDecimal getTotal()
//    {
//        BigDecimal total = items.values()
//                                .stream()
//                                .map(i -> i.getLineTotal())
//                                .reduce( BigDecimal.ZERO, (lineTotal, subTotal) -> subTotal.add(lineTotal));
//
//        return total;
//    }
}
