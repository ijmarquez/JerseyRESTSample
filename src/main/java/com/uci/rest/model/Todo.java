package com.uci.rest.model;


import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

//public class Todo {
//    private String summary;
//    private String description;
//    private int id;
//
//    public String getSummary() {
//        return summary;
//    }
//
//    public void setSummary(String summary) {
//        this.summary = summary;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//}

/****************/

public class Todo {

    private String productID;
    private String itemName;    // unique id
    private String location;
    private String displayName;


    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductID() {
        return productID;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemName() {
        return itemName;
    }


    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }







}