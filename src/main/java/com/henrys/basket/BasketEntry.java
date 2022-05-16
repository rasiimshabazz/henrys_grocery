package com.henrys.basket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasketEntry {

    private final StockItem item;
    private final int quantity;

    public BasketEntry(StockItem item, int quantity) {
        this.item = item;
        this.quantity = Math.max(quantity, 0);
    }

    public StockItem getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    double price() {
        return this.item.getCost() * this.quantity;
    }

    public String toString() {
        return this.quantity + " " + this.item;
    }

    static BasketEntry mergeByStockItem(List<BasketEntry> repeatedEntries, StockItem distinctItem) {
        int quantity = repeatedEntries.stream()
                .filter(repeat -> repeat.getItem().equals(distinctItem))
                .mapToInt(BasketEntry::getQuantity).sum();
        return new BasketEntry(distinctItem, quantity);
    }


    static List<BasketEntry> mergeBasketEntries(List<BasketEntry> unmergedBasketEntries) {

        BasketEntry.filterOutNulls(unmergedBasketEntries);
        List<StockItem> distinctItems = unmergedBasketEntries.stream()
                .map(BasketEntry::getItem)
                .distinct().collect(Collectors.toList());
        return distinctItems.stream()
                .map(distinctItem -> mergeByStockItem(unmergedBasketEntries, distinctItem))
                .collect(Collectors.toList());
    }

    static List<BasketEntry> filterOutNulls(List<BasketEntry> newBasketEntries) {
        return newBasketEntries.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
