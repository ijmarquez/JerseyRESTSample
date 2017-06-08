package com.uci.rest.model;

/**
 * Created by Calvin on 6/7/2017.
 */
public class OrderDetails {
    private int id;
    private String orderId;
    private String productId;
    private String itemSize;
    private double unitPrice;
    private int quantity;
    private double total;

    public void setId (int id) { this.id = id; }
    public int getId () {return this.id; }

    public void setOrderId (String orderId) { this.orderId = orderId; }
    public String getOrderId () {return this.orderId; }

    public void setProductId (String productId) { this.productId = productId; }
    public String getProductId () { return this.productId; }

    public void setItemSize (String itemSize) { this.itemSize = itemSize; }
    public String getItemSize () {return this.itemSize; }

    public void setUnitPrice (double unitPrice) { this.unitPrice = unitPrice; }
    public double getUnitPrice () {return this.unitPrice; }

    public void setQuantity (int quantity) { this.quantity = quantity; }
    public int getQuantity () {return this.quantity; }

    public void setTotal (double total) { this.total = total; }
    public double getTotal () {return this.total; }
}
