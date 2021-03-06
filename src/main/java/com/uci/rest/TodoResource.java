package com.uci.rest;

import com.uci.rest.model.*;
import com.uci.rest.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
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

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addTodo(@FormParam("firstName") String firstName,
                            @FormParam("lastName") String lastName,
                            @FormParam("emailAddress") String emailAddress,
                            @FormParam("phoneArea") String phoneArea,
                            @FormParam("phoneThree") String phoneThree,
                            @FormParam("phoneFour") String phoneFour,
                            @FormParam("ccType") String ccType,
                            @FormParam("creditCardNumber") String creditCardNumber,
                            @FormParam("ccExpire") String ccExpire,
                            @FormParam("billAddress") String billAddress,
                            @FormParam("billCity") String billCity,
                            @FormParam("billState") String billState,
                            @FormParam("billZipCode") String billZipCode,
                            @FormParam("shipAddress") String shipAddress,
                            @FormParam("shipCity") String shipCity,
                            @FormParam("shipState") String shipState,
                            @FormParam("shipZipCode") String shipZipCode,
                            @FormParam("itemName") String itemName,
                            @FormParam("itemSize") String itemSize,
                            @FormParam("quantity") String quantity,
                            @FormParam("unitPrice") String unitPrice,
                            @FormParam("total") String total){
        Customer todo = new Customer();

        todo.setFirstName(firstName);
        todo.setLastName(lastName);
        todo.setEmailAddress(emailAddress);
        todo.setPhoneNumber(phoneArea+phoneThree+phoneFour);

        todo.setCcType(ccType);
        todo.setCcNumber(creditCardNumber);
        todo.setCcExpire(ccExpire);

        todo.setBillAddress(billAddress);
        todo.setBillCity(billCity);
        todo.setBillState(billState);
        todo.setBillZipCode(Integer.parseInt(billZipCode));

        todo.setShipAddress(shipAddress);
        todo.setShipCity(shipCity);
        todo.setShipState(shipState);
        todo.setShipZipCode(Integer.parseInt(shipZipCode));

        todo.setItemPurchase(itemName);

        todo.setItemSize(itemSize);
        todo.setQuantity(Integer.parseInt(quantity));
        todo.setUnitPrice(Double.parseDouble(unitPrice));
        todo.setTotal(Double.parseDouble(total));

        System.out.println(todo);

        if(TodoService.AddNewCustomer(todo)) {
            java.net.URI location = null;
            try {
                location = new java.net.URI("../../../pa3/OrderDetails");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return Response.temporaryRedirect(location).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(todo).build();
    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateTodo(@PathParam("id") int id, Customer todo) {

        // Retrieve the todo that you will need to change
        Customer retrievedTodo = TodoService.customerObject(id);

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
        Customer retrievedOrderDetails = TodoService.customerObject(id);

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