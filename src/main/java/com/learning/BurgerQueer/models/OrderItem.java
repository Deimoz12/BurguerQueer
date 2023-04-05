package com.learning.BurgerQueer.models;

import lombok.Data;

@Data
public class OrderItem {

    private String itemName;
    private Double itemPrice;
    private Double itemQuantity;

}
