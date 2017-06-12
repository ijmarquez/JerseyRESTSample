PA4: Building a web application using JSP and RESTful web services

GROUP MEMBERS: Calvin Poon and Irish Marquez

URL: http://andromeda-74.ics.uci.edu:23456/pa3/home

CLIENT FILES ARE IN: pa3.war and PA4_Client
       
REST FILES ARE IN: jerseyrest.war and PA4_REST




JSP IMPLEMENTATION

JSP is implemented on the product list page. 

URL: http://andromeda-74.ics.uci.edu:23456/pa3/itemsList.jsp

War file: pa3.war

Folder: PA4 _Client

File name: itemsList.jsp and MainItems.java




REST SERVICES

REST service application is written in Java using Jersey REST framework using JSON.

Folder: PA4_REST

File names: TodoService.java and TodoResource.java



a. GET

Folder: PA4_REST

getTodoById() – used to get specific item in client’s Item.java 

getAllTodos() – used to get all items in client’s MainItems.java

Validation: Throws 404 error



b. PUT

Folder: PA4_REST

updateTodo() – used to update the customer’s information

customerObject() – used to update the customer’s information

Validation: Throws 404 error



c. POST

Folder: PA4_REST

addCustomer() – used to store customer info from client’s CheckOut.jsp



d. DELETE

Folder: PA4_REST

File name: TodoService.Java

deleteTodo() – used to delete customer from database

Validation: Throws 404 error




REST CALLS

REST calls are found in the PA4_REST folder named “TODO RESTService calls.postman_collection.” 

This includes the method type, request url, sample response, and sample request for each RESTful 

service method implemented.
