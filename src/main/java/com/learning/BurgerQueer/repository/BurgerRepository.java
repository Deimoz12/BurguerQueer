package com.learning.BurgerQueer.repository;


import com.learning.BurgerQueer.models.OrderTicket;
import com.learning.BurgerQueer.models.Site;
import org.springframework.stereotype.Repository;

@Repository
public class BurgerRepository {

    public Double getDiscounts(Long siteId) {
        return 7.0;
    }

    public boolean saveTicketData(OrderTicket ticket) {
        return true;
    }

    public String getSiteNameById(Long id) {
        return "";
    }

}
