package com.uci.rest.model;

/**
 * Created by Calvin on 6/9/2017.
 */
public class Cart {
    private String name;
    private int qty, totalCost;
    private String size;
    public Cart() { }

    public Cart(String name, Integer quantity, String size) {
        this.name = name;
        this.qty = quantity;
        this.size = size;
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public int getQty() { return qty; }
    public int getTotalCost() {return totalCost; }
    public void setTotalCost (int totalCost) { this.totalCost = totalCost; }
}
