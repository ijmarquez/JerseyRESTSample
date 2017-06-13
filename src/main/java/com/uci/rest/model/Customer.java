package com.uci.rest.model;

/**
 * Created by irishmarquez on 6/3/17.
 */
public class Customer {

    int id;
    String firstName;
    String lastName;
    String emailAddress;
    String phoneNumber;

    String ccType;
    String ccNumber;
    String ccExpire;

    String billAddress;
    String billCity;
    String billState;
    int    billZipCode;

    String shipAddress;
    String shipCity;
    String shipState;
    int shipZipCode;

    String deliveryType;

    String itemPurchase;
    String itemSize;
    int quantity;
    double unitPrice;
    double total;

    public void setId(int id) { this.id = id;}
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setCcType(String ccType) { this.ccType = ccType; }
    public void setCcNumber(String ccNumber) { this.ccNumber = ccNumber; }
    public void setCcExpire(String ccExpire) { this.ccExpire = ccExpire; }

    public void setBillAddress(String billAddress) { this.billAddress = billAddress; }
    public void setBillCity(String billCity) { this.billCity = billCity; }
    public void setBillState(String billState) { this.billState = billState; }
    public void setBillZipCode(int billZipCode) { this.billZipCode = billZipCode; }

    public void setShipAddress(String shipAddress) { this.shipAddress = shipAddress; }
    public void setShipCity(String shipCity) { this.shipCity = shipCity; }
    public void setShipState(String shipState) { this.shipState = shipState; }
    public void setShipZipCode(int shipZipCode) { this.shipZipCode = shipZipCode; }

    public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }
    public void setItemPurchase(String itemPurchase) { this.itemPurchase = itemPurchase; }

    public void setItemSize (String itemSize) { this.itemSize = itemSize; }
    public void setQuantity (int quantity) { this.quantity = quantity; }
    public void setUnitPrice (double unitPrice) { this.unitPrice = unitPrice; }
    public void setTotal(double total) { this.total = total; }

    public int getId() { return this.id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmailAddress() { return emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }

    public String getCcType() { return ccType; }
    public String getCcNumber() { return ccNumber; }
    public String getCcExpire() { return ccExpire; }

    public String getBillAddress() { return billAddress; }
    public String getBillCity() { return billCity; }
    public String getBillState() { return billState; }
    public int    getBillZipCode() { return billZipCode; }

    public String getShipAddress() { return shipAddress; }
    public String getShipCity() { return shipCity; }
    public String getShipState() { return shipState; }
    public int getShipZipCode() { return shipZipCode; }

    public String getDeliveryType() { return deliveryType; }
    public String getItemPurchase() { return itemPurchase; }
  
    public String getItemSize() { return this.itemSize; }
    public int getQuantity() { return this.quantity; }
    public double getUnitPrice() { return this.unitPrice; }
    public double getTotal() {return this.total; }
}
