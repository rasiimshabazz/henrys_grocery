package com.henrys.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasketEntries {

    private final List<BasketEntry> basketEntries;

    public BasketEntries(List<BasketEntry> basketEntries) {
        if (basketEntries == null) basketEntries = new ArrayList<>();
        this.basketEntries = mergeBasketEntries(basketEntries);
    }

    public double fullPrice() {
        return this.basketEntries.stream()
                .mapToDouble(BasketEntry::price)
                .sum();
    }

    public List<BasketEntry> getEntries() {
        return this.basketEntries;
    }

    public boolean contains(StockItem stockItem) {
        return this.basketEntries.stream().anyMatch(item -> item.getItem().equals(stockItem));
    }

    public boolean isBuyingAtLeastTwoSoups(StockItem stockItem, int amount) {
        return this.basketEntries.stream()
                .filter(item -> stockItem.equals(item.getItem()))
                .findFirst().orElse(new BasketEntry(stockItem, 0))
                .getQuantity() >= amount;
    }

    public int count(StockItem stockItem) {
        return this.basketEntries.stream().filter(item ->
                item.getItem().equals(stockItem)
        ).findFirst().orElse(new BasketEntry(stockItem, 0)).getQuantity();
    }

    public String toString() {
        return this.basketEntries.toString();
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
