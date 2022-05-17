package com.henrys.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasketEntries {

    private final List<BasketEntry> entries;

    public BasketEntries(List<BasketEntry> entries) {
        if (entries == null) entries = new ArrayList<>();
        this.entries = mergeBasketEntries(entries);
    }

    public double price() {
        return this.entries.stream()
                .mapToDouble(BasketEntry::price)
                .sum();
    }

    public int count(StockItem stockItem) {
        return this.entries.stream()
                .filter(basketEntry -> basketEntry.getItem().equals(stockItem))
                .findFirst().orElse(new BasketEntry(stockItem, 0))
                .getQuantity();
    }

    public String toString() {
        return this.entries.toString();
    }

    private static List<BasketEntry> mergeBasketEntries(List<BasketEntry> unmergedBasketEntries) {

        filterOutNulls(unmergedBasketEntries);
        List<StockItem> distinctItems = unmergedBasketEntries.stream()
                .map(BasketEntry::getItem)
                .distinct().collect(Collectors.toList());
        return distinctItems.stream()
                .map(distinctItem -> mergeByStockItem(unmergedBasketEntries, distinctItem))
                .collect(Collectors.toList());
    }

    private static BasketEntry mergeByStockItem(List<BasketEntry> repeatedEntries, StockItem distinctItem) {
        int quantity = repeatedEntries.stream()
                .filter(repeat -> repeat.getItem().equals(distinctItem))
                .mapToInt(BasketEntry::getQuantity).sum();
        return new BasketEntry(distinctItem, quantity);
    }

    private static List<BasketEntry> filterOutNulls(List<BasketEntry> newBasketEntries) {
        return newBasketEntries.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
