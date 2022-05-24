package com.henrys.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasketEntries {

    private List<BasketEntry> entries;

    public BasketEntries(List<BasketEntry> entries) {
        this.entries = (entries == null) ? new ArrayList<>() : entries;
        this.entries = mergeBasketEntries();
    }

    public double price() {
        return this.entries.stream()
                .mapToDouble(BasketEntry::price)
                .sum();
    }

    public int count(StockItem stockItem) {
        return this.entries.stream()
                .filter(basketEntry -> basketEntry.sameAs(stockItem))
                .findFirst().orElse(new BasketEntry(stockItem, 0))
                .getQuantity();
    }

    public String stringValue() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < this.entries.size(); i++) {
            BasketEntry entry = this.entries.get(i);
            result.append(entry.stringValue());
            if (i < this.entries.size()-1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    private List<BasketEntry> mergeBasketEntries() {

        return findDistinctItems().stream()
                .map(distinctItem -> mergeByStockItem(distinctItem))
                .collect(Collectors.toList());
    }

    private List<StockItem> findDistinctItems() {
        List<StockItem> distinctItems = this.entries.stream()
                .map(BasketEntry::getItem)
                .distinct().collect(Collectors.toList());
        return distinctItems;
    }

    private BasketEntry mergeByStockItem(StockItem distinctItem) {
        int quantity = this.entries.stream()
                .filter(repeat -> repeat.sameAs(distinctItem))
                .mapToInt(BasketEntry::getQuantity).sum();
        return new BasketEntry(distinctItem, quantity);
    }

}
