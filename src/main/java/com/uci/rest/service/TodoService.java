package com.uci.rest.service;


import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {


    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM MainProduct";
    private final static String SINGLE_ITEM_INFO = "SELECT * FROM `MainProduct`,`Product`WHERE MainProduct.ID = Product.ID && MainProduct.generalName = \"";

    //GET section
    // use for displaying item detail
    public static List<ItemList> getTodoById(String generalName) {
        List<ItemList> todos = new ArrayList<ItemList>();

        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, SINGLE_ITEM_INFO + generalName + "\"");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    ItemList todo = new ItemList();

                    todo.setProductID(resultSet.getString("ID"));
                    todo.setGeneralName(resultSet.getString("generalName"));
                    todo.setLocation(resultSet.getString("Location"));
                    todo.setItemName(resultSet.getString("Display Name"));
                    todo.setDescription(resultSet.getString("description"));
                    todo.setCost(resultSet.getString("cost"));
                    todos.add(todo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return todos;
    }

    // use for displaying all items in homepage
    public static List<MainList> getAllTodos() {
        List<MainList> todos = new ArrayList<MainList>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    MainList todo = new MainList();

                    todo.setProductID(resultSet.getString("ID"));
                    todo.setGeneralName(resultSet.getString("generalName"));
                    todo.setLocation(resultSet.getString("imageLocation"));
                    todo.setCost(resultSet.getString("cost"));

                    todos.add(todo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return todos;
    }
    //END GET section

    //POST section
    // use to add customer info into db
    public static boolean AddNewCustomer(Customer todo) {

        String sql = "INSERT INTO Customer (firstName, lastName, emailAddress, phoneNumber, ccType, creditCardNumber, ccExpire, billAddress, billCity, billState, billZipCode, shipAddress, shipCity, shipState, shipZipCode, itemPurchase, itemSize, quantity, unitPrice, total) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql,
                todo.getFirstName(), todo.getLastName(), todo.getEmailAddress(), todo.getPhoneNumber(),
                todo.getCcType(), todo.getCcNumber(), todo.getCcExpire(),
                todo.getBillAddress(), todo.getBillCity(), todo.getBillState(), String.valueOf(todo.getBillZipCode()),
                todo.getShipAddress(), todo.getShipCity(), todo.getShipState(), String.valueOf(todo.getShipZipCode()),
                todo.getItemPurchase(), todo.getItemSize(), String.valueOf(todo.getQuantity()),
                String.valueOf(todo.getUnitPrice()), String.valueOf(todo.getTotal()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
    //END POST section

    //PUT section
    //get info from DB and store it to a Customer object
    public static Customer customerObject(int id) {

        String CustomerDetails = "SELECT * FROM Customer WHERE Customer.id = "+id;
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, CustomerDetails);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Customer customer = new Customer();

                    customer.setId(resultSet.getInt("ID"));
                    customer.setFirstName(resultSet.getString("firstName"));
                    customer.setLastName(resultSet.getString("lastName"));
                    customer.setEmailAddress(resultSet.getString("emailAddress"));
                    customer.setPhoneNumber(resultSet.getString("phoneNumber"));

                    customer.setCcType(resultSet.getString("ccType"));
                    customer.setCcNumber(resultSet.getString("creditCardNumber"));
                    customer.setCcExpire(resultSet.getString("ccExpire"));

                    customer.setBillAddress(resultSet.getString("billAddress"));
                    customer.setBillCity(resultSet.getString("billCity"));
                    customer.setBillState(resultSet.getString("billState"));
                    customer.setBillZipCode(resultSet.getInt("billZipCode"));

                    customer.setShipAddress(resultSet.getString("shipAddress"));
                    customer.setShipCity(resultSet.getString("shipCity"));
                    customer.setShipState(resultSet.getString("shipState"));
                    customer.setShipZipCode(resultSet.getInt("shipZipCode"));

                    customer.setDeliveryType(resultSet.getString("deliveryType"));
                    customer.setItemPurchase(resultSet.getString("itemPurchase"));
                    customer.setItemSize(resultSet.getString("itemSize"));
                    customer.setQuantity(resultSet.getInt("quantity"));
                    customer.setUnitPrice(resultSet.getDouble("unitPrice"));

                    return customer;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //method that updates Customer information if different from DB
    public static boolean updateTodo(Customer todo) {

        String sql = "UPDATE Customer SET firstName=?, lastName=?, emailAddress=?, phoneNumber=?, ccType=?, creditCardNumber=?, " +
                "ccExpire=?, billAddress=?, billCity=?, billState=?, billZipCode=?, shipAddress=?, shipCity=?, shipState=?," +
                "shipZipCode=?, deliveryType=?, itemPurchase=? WHERE Customer.ID=?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql,
                todo.getFirstName(), todo.getLastName(), todo.getEmailAddress(), todo.getPhoneNumber(), todo.getCcType(),
                todo.getCcNumber(), todo.getCcExpire(), todo.getBillAddress(), todo.getBillCity(), todo.getBillState(),
                String.valueOf(todo.getBillZipCode()), todo.getShipAddress(), todo.getShipCity(), todo.getShipState(),
                String.valueOf(todo.getShipZipCode()), todo.getDeliveryType(), todo.getItemPurchase(), String.valueOf(todo.getId()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
    //END of PUT section


    //DELETE section
    //Uses PUT Section CustomerObject method to create an object of the customer if it exist in the DB
    //delete customer information by ID
    public static boolean deleteTodo(Customer retrieveOrderDetail) {

        String sql = "DELETE FROM Customer WHERE ID = ?;";
        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrieveOrderDetail.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }
    //END of DELETE section
}

