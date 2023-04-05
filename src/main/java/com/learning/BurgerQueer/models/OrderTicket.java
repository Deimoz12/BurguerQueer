package com.learning.BurgerQueer.models;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Data
public class OrderTicket {

    private final String siteName;
    private LocalDateTime localDateTime;
    private final Long siteId;
    private final Map<String, Double> items;
    private final Double finalPrice;
    private final List<Double> appliedDiscounts;
    private final String greetMessage = "QueerBurger is happy (FOR YOU) to SUBMIT to y(U)o(S)u (>):) ";


    public OrderTicket(String siteName, Long siteId, Map<String, Double> items, Double finalPrice, List<Double> appliedDiscounts) {
        this.siteName = siteName;
        this.siteId = siteId;
        this.items = items;
        this.finalPrice = finalPrice;
        this.appliedDiscounts = appliedDiscounts;
        this.localDateTime = getLocalDateTime();
    }
}
