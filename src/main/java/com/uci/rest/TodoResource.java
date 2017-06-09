package com.uci.rest;

import com.uci.rest.model.*;
import com.uci.rest.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */
//todos will be the pathsegment that precedes any path segment specified by @Path on a method.
@Path("/todos")
public class TodoResource {

    //This method represents an endpoint with the URL /todos/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    //PUT section of single item
    @Path("{generalName}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getTodoById(
            @PathParam("generalName") String generalName){

        List<ItemList> todoList = TodoService.getTodoById(generalName);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(todoList == null || todoList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(todoList).build();
    }
    //END of PUT of single item section

    //This method represents an endpoint with the URL /todos and a GET request.
    // Since there is no @PathParam mentioned, the /todos as a relative path and a GET request will invoke this method.
    //GET all items section
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllTodos() {
        List<MainList> todoList = TodoService.getAllTodos();

        if(todoList == null || todoList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(todoList).build();
    }
    //END of GET all items section

    //This method represents an endpoint with the URL /todos and a POST request.
    // Since there is no @PathParam mentioned, the /todos as a relative path and a POST request will invoke this method.
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
//    public Response addTodo(Todo todo) {
//
//        //The todo object here is automatically constructed from the json request. Jersey is so cool!
//        if(TodoService.AddTodo(todo)) {
//            return Response.ok().entity("TODO Added Successfully").build();
//        }
//
//        // Return an Internal Server error because something wrong happened. This should never be executed
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//
//
//    }

    //Similar to the method above.
//    @POST
//    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
//    //If you were to send a POST through a form submit, this method would be called.
//    public Response addTodo(@FormParam("summary") String summary,
//                            @FormParam("description") String description) {
//        Todo todo = new Todo();
//        todo.setSummary(summary);
//        todo.setDescription(description);
//
//        System.out.println(todo);
//
//        if(TodoService.AddTodo(todo)) {
//            return Response.ok().entity("TODO Added Successfully").build();
//        }
//
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addCustomer(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("emailAddress") String emailAddress,

            @FormParam("phoneArea")  String phoneArea,
            @FormParam("phoneThree") String phoneThree,
            @FormParam("phoneFour") String phoneFour,

            @FormParam("ccType") String ccType,
            @FormParam("creditCardNumber") String creditCardNumber,
            @FormParam("ccExpire") String ccExpire,

            @FormParam("billAddress") String billAddress,
            @FormParam("billCity") String billCity,
            @FormParam("billState") String billState,
            @FormParam("billZipCode") int billZipCode,

            @FormParam("shipAddress") String shipAddress,
            @FormParam("shipCity") String shipCity,
            @FormParam("shipState") String shipState,
            @FormParam("shipZipCode") int shipZipCode,
            @FormParam("deliveryType") String deliveryType)
    {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmailAddress(emailAddress);
        //customer.setPhoneNumber(phoneArea, phoneThree, phoneFour);
        customer.setPhoneNumber("1234567890");

        customer.setCcType(ccType);
        customer.setCcNumber(creditCardNumber);
        customer.setCcExpire(ccExpire);

        customer.setBillAddress(billAddress);
        customer.setBillCity(billCity);
        customer.setBillState(billState);
        customer.setBillZipCode(billZipCode);

        customer.setShipAddress(shipAddress);
        customer.setShipCity(shipCity);
        customer.setShipState(shipState);
        customer.setShipZipCode(shipZipCode);
        customer.setDeliveryType(deliveryType);

        System.out.println(customer);

        if(TodoService.addCustomer(customer)) {
            return Response.ok().entity("Customer Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }








    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateTodo(@PathParam("id") int id, Customer todo) {

        // Retrieve the todo that you will need to change
        Customer retrievedTodo = TodoService.customerObject(id);
        todo = new Customer();      //for testing purposes only
        if(retrievedTodo == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // This is the todo_object retrieved from the json request sent.
        todo.setId(id);

        // if the user has provided null, then we set the retrieved values.
        // This is done so that a null value is not passed to the todo object when updating it.
        if(todo.getFirstName() == null) { todo.setFirstName(retrievedTodo.getFirstName()); }
        if(todo.getLastName() == null) { todo.setLastName(retrievedTodo.getLastName()); }
        if(todo.getEmailAddress() == null) { todo.setEmailAddress(retrievedTodo.getEmailAddress()); }
        if(todo.getPhoneNumber() == null) { todo.setPhoneNumber(retrievedTodo.getPhoneNumber()); }

        if(todo.getCcType() == null) { todo.setCcType(retrievedTodo.getCcType()); }
        if(todo.getCcNumber() == null) { todo.setCcNumber(retrievedTodo.getCcNumber()); }
        if(todo.getCcExpire() == null) { todo.setCcExpire(retrievedTodo.getCcExpire()); }

        if(todo.getBillAddress() == null) { todo.setBillAddress(retrievedTodo.getBillAddress()); }
        if(todo.getBillCity() == null) { todo.setBillCity(retrievedTodo.getBillCity()); }
        if(todo.getBillState() == null) { todo.setBillState(retrievedTodo.getBillState()); }
        if(todo.getBillZipCode() != retrievedTodo.getBillZipCode()) { todo.setBillZipCode(retrievedTodo.getBillZipCode()); }

        if(todo.getShipAddress() == null) { todo.setShipAddress(retrievedTodo.getShipAddress()); }
        if(todo.getShipCity() == null) { todo.setShipCity(retrievedTodo.getShipCity()); }
        if(todo.getShipState() == null) { todo.setShipState(retrievedTodo.getShipState()); }
        if(todo.getShipZipCode() != retrievedTodo.getShipZipCode()) { todo.setShipZipCode(retrievedTodo.getShipZipCode()); }

        if(todo.getDeliveryType() == null) { todo.setDeliveryType(retrievedTodo.getDeliveryType()); }
        if(todo.getItemPurchase() == null) { todo.setItemPurchase(retrievedTodo.getItemPurchase()); }

        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(TodoService.updateTodo(todo)) {
            return Response.ok().entity(todo).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

//    This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @Path("{id}")
    @DELETE
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response deleteTodo(@PathParam("id") int id) {

        //Retrieve the todo_object that you want to delete.
        OrderDetails retrievedOrderDetails = TodoService.deleteObject(id);

        if(retrievedOrderDetails == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(TodoService.deleteTodo(retrievedOrderDetails)) {
            return Response.ok().entity("Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}