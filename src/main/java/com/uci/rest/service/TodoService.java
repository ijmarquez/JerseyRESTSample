package com.uci.rest.service;

import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Todo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariqibrahim on 5/28/17.
 */
//public class TodoService {
//
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


public class TodoService {


    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM MainProduct";


    // use for displaying item detail
    public static Todo getTodoById(String itemName) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY + " WHERE PRODUCT_ID = " + itemName);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Todo todo = new Todo();

                    todo.setProductID(resultSet.getString("productID"));
                    todo.setItemName(resultSet.getString("Item Name"));
                    todo.setLocation(resultSet.getString("Location"));
                    todo.setDisplayName(resultSet.getString("Display Name"));

                    return todo;

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


    // use for displaying all items in homepage
    public static List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<Todo>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Todo todo = new Todo();

                    todo.setProductID(resultSet.getString("productID"));
                    todo.setItemName(resultSet.getString("Item Name"));
                    todo.setLocation(resultSet.getString("Location"));
                    todo.setDisplayName(resultSet.getString("Display Name"));

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
    public static boolean AddNewCustomer(Todo todo) {

        String sql = "INSERT INTO TODOS  (TODO_SUMMARY, TODO_DESC)" +
                "VALUES (?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription());

    }

    public static boolean updateTodo(Todo todo) {

        String sql = "UPDATE TODOS SET TODO_SUMMARY=?, TODO_DESC=? WHERE TODO_ID=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, todo.getSummary(), todo.getDescription(),
                String.valueOf(todo.getItemName()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteTodo(Todo retrievedTodo) {

        String sql = "DELETE FROM TODOS WHERE TODO_ID=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedTodo.getItemName()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
}
