package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class Henry {

    BigDecimal priceBasket(Basket basket) {

        if (basket.isEmpty()) return BigDecimal.ZERO;

        List<StockItem> basketItems = basket.getItems();

        if (basketItems.get(0).equals(StockItem.SOUP))
            return BigDecimal.valueOf(StockItem.SOUP.getCost());

        return BigDecimal.ZERO;
    }
}
