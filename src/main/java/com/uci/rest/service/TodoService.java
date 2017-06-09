package com.uci.rest.service;


import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Customer;
import com.uci.rest.model.ItemList;
import com.uci.rest.model.MainList;
import com.uci.rest.model.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariqibrahim on 5/28/17.
 */

public class TodoService {


    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM MainProduct";
    private final static String SINGLE_ITEM_INFO = "SELECT * FROM `MainProduct`,`Product`WHERE MainProduct.ID = Product.ID && MainProduct.generalName = \"";
//    private final static String DELETE_ORDER_DETAILS = "SELECT * FROM `OrderDetails` WHERE OrderDetails.id = 23";

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


    // use to add customer info into db
    //POST section
    public static boolean AddNewCustomer(Customer todo) {

        String sql = "INSERT INTO Customer (firstName, lastName, emailAddress, phoneNumber, ccType, creditCardNumber, ccExpire, billAddress, billCity, billState, billZipCode, shipAddress, shipCity, shipState, shipZipCode, itemPurchase, total) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql,
                todo.getFirstName(), todo.getLastName(), todo.getEmailAddress(), todo.getPhoneNumber(),
                todo.getCcType(), todo.getCcNumber(), todo.getCcExpire(),
                todo.getBillAddress(), todo.getBillCity(), todo.getBillState(), String.valueOf(todo.getBillZipCode()),
                todo.getShipAddress(), todo.getShipCity(), todo.getShipState(), String.valueOf(todo.getShipZipCode()),
                todo.getItemPurchase(), String.valueOf(todo.getTotal()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }

    public static boolean addItemToOrder(Customer todo) {

        String sql = "INSERT INTO OrderDetails (orderId, productId, itemSize, unitPrice, quantity, total)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        Connection connection = DatabaseConnector.getConnection();
        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql,
                todo.getFirstName(), todo.getLastName(), todo.getEmailAddress(), todo.getPhoneNumber(),
                todo.getCcType(), todo.getCcNumber(), todo.getCcExpire(),
                todo.getBillAddress(), todo.getBillCity(), todo.getBillState(), String.valueOf(todo.getBillZipCode()),
                todo.getShipAddress(), todo.getShipCity(), todo.getShipState(), String.valueOf(todo.getShipZipCode()),
                todo.getItemPurchase(), String.valueOf(todo.getTotal()));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
    //END POST section

    //PUT section
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
    public static OrderDetails deleteObject(int id) {

        String DELETE_ORDER_DETAILS = "SELECT * FROM OrderDetails WHERE OrderDetails.id = "+id;
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, DELETE_ORDER_DETAILS);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    OrderDetails orderDetails = new OrderDetails();

                    orderDetails.setId(resultSet.getInt("id"));
                    orderDetails.setOrderId(resultSet.getString("orderId"));
                    orderDetails.setProductId(resultSet.getString("productId"));
                    orderDetails.setItemSize(resultSet.getString("itemSize"));
                    orderDetails.setUnitPrice(resultSet.getDouble("unitPrice"));
                    orderDetails.setQuantity(resultSet.getInt("quantity"));
                    orderDetails.setTotal(resultSet.getDouble("total"));

                    return orderDetails;
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

    public static boolean deleteTodo(OrderDetails retrieveOrderDetail) {

        String sql = "DELETE FROM OrderDetails WHERE id = ?;";
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

//public class TodoService {

//
//    private final static String ALL_TODOS_QUERY = "SELECT * FROM TODOS";
//
//    public static Todo getTodoById(int id) {
//        //Get a new connection object before going forward with the JDBC invocation.
//        Connection connection = DatabaseConnector.getConnection();
//        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_TODOS_QUERY + " WHERE TODO_ID = " + id);
//
//        if (resultSet != null) {
//            try {
//                while (resultSet.next()) {
//                    Todo todo = new Todo();
//
//
//                    todo.setId(resultSet.getInt("TODO_ID"));
//                    todo.setDescription(resultSet.getString("TODO_DESC"));
//                    todo.setSummary(resultSet.getString("TODO_SUMMARY"));
//
//                    return todo;
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//
//                    // We will always close the connection once we are done interacting with the Database.
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//
//
//    }
//
//    public static List<Todo> getAllTodos() {
//        List<Todo> todos = new ArrayList<Todo>();
//
//        Connection connection = DatabaseConnector.getConnection();
//        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_TODOS_QUERY);
//
//        if (resultSet != null) {
//            try {
//                while (resultSet.next()) {
//                    Todo todo = new Todo();
//
//                    todo.setId(resultSet.getInt("TODO_ID"));
//                    todo.setDescription(resultSet.getString("TODO_DESC"));
//                    todo.setSummary(resultSet.getString("TODO_SUMMARY"));
//
//                    todos.add(todo);
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return todos;
//    }
//
//    public static boolean AddTodo(Todo todo) {
//
//        String sql = "INSERT INTO TODOS  (TODO_SUMMARY, TODO_DESC)" +
//                "VALUES (?, ?)";
//        Connection connection = DatabaseConnector.getConnection();
//        return DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription());
//
//    }
//
//    public static boolean updateTodo(Todo todo) {
//
//        String sql = "UPDATE TODOS SET TODO_SUMMARY=?, TODO_DESC=? WHERE TODO_ID=?;";
//
//        Connection connection = DatabaseConnector.getConnection();
//
//        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription(),
//                String.valueOf(todo.getId()));
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return updateStatus;
//
//    }
//
//    public static boolean deleteTodo(Todo retrievedTodo) {
//
//        String sql = "DELETE FROM TODOS WHERE TODO_ID=?;";
//
//        Connection connection = DatabaseConnector.getConnection();
//
//        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedTodo.getId()));
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return updateStatus;
//    }
//}

/**************************/

