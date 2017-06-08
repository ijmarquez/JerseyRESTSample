package com.uci.rest.model;

/**
 * Created by Calvin on 6/7/2017.
 */
public class MainList {
    private String productID;
    private String location;
    private String generalName;
    private String cost;

    public void setGeneralName(String generalName) { this.generalName = generalName; }
    public String getGeneralName() { return  generalName; }

    public void setCost(String cost) { this.cost = cost; }
    public String getCost() { return  cost; }

    public void setProductID(String productID) { this.productID = productID; }
    public String getProductID() { return productID; }


    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }
}
