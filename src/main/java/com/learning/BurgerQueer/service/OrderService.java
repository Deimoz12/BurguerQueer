package com.learning.BurgerQueer.service;

import com.learning.BurgerQueer.models.OrderItem;
import com.learning.BurgerQueer.models.OrderTicket;
import com.learning.BurgerQueer.models.UserData;
import com.learning.BurgerQueer.repository.BurgerRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {

    private List<Double> appliedDiscounts;

    private BurgerRepository burgerRepository;

    /*recibir el pedido con las especificaciones
     * a esta altura ya deberia de alguna forma haber cargado los precios de los objetos
     * tendria que poder diferenciar si el usuario quiere una hamburguesa u otra cosa,
     * verificar si el usuario es vip, posiblemente previo a esto tambien.
     * calcular descuentos acorde a eso
     * verificar descuentos vigentes */

    private OrderTicket buildOrder(List<OrderItem> items, boolean vip, UserData userData, boolean seasonDiscount, Long siteId) {
        Double finalPrice = 0.0;

        for (OrderItem item : items) {
            finalPrice += item.getItemPrice() * item.getItemQuantity();
        }

        if (vip) {
            addOrderPoints(finalPrice, userData);
        }

        finalPrice = checkDiscounts(finalPrice, vip, seasonDiscount, siteId);

        OrderTicket orderTicket = buildOrderTicket(siteId, items, finalPrice);

        burgerRepository.saveTicketData(orderTicket);
        return orderTicket;


    }

    private OrderTicket buildOrderTicket(Long siteId, List<OrderItem> items, Double finalPrice) {
        /*que tendria que tener el ticket de la venta?
         * cuando,donde(empresa+sede),que,cuanto(detalle de que y dtos), algun msj*/

        String siteName = burgerRepository.getSiteNameById(siteId);
        Map<String, Double> parsedItems = items.stream().collect(Collectors.toMap(OrderItem::getItemName, item -> item.getItemPrice() * item.getItemQuantity()));


        OrderTicket orderTicket = new OrderTicket(siteName, siteId, parsedItems, finalPrice, appliedDiscounts);
        appliedDiscounts.clear();
        return orderTicket;

    }


    private Double checkDiscounts(Double originalPrice, boolean vip, boolean seasonDiscount, Long siteId) {
        Double siteDiscount = 0.0;

        if (seasonDiscount) {
            Double sDiscount = burgerRepository.getDiscounts(siteId);
            siteDiscount += sDiscount;
            appliedDiscounts.add(sDiscount);
        }

        if (vip) {
            siteDiscount += 15.0;
            appliedDiscounts.add(15.0);
        }

        return originalPrice * (1 - siteDiscount / 100);

    }

    private void addOrderPoints(Double finalPrice, UserData userData) {
    }
}
