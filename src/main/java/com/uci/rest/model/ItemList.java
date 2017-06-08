package com.uci.rest.model;

/**
 * Created by Calvin on 6/7/2017.
 */
public class ItemList {

    private String productID;
    private String itemName;
    private String location;
    private String generalName;
    private String cost;
    private String description;

    public void setProductID(String productID) { this.productID = productID; }
    public String getProductID() { return productID; }

    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getItemName() { return itemName; }

    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }

    public void setGeneralName(String generalName) { this.generalName = generalName; }
    public String getGeneralName() { return  generalName; }

    public void setCost(String cost) { this.cost = cost; }
    public String getCost() { return  cost; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }
}
