<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="database.*" %>
<%@ page import="controller.*" %>
<% User user = null;
user = (User)session.getAttribute("user");
if(user==null)
{
	response.sendRedirect("login.html");
	return;
}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./css/css/login.css" />
    <title>Update</title>
  </head>
  <body>
    <div class="container to-center">
      <div class="row align-items-center">
        <div class="col-sm-3"></div>
        <div class="col-sm-6" style="margin-top: 250px;">
          <h1 style="margin-bottom: 40px; color:orange">
            Update Info :
          </h1>
          <form action="Update" method="POST">
            <div class="form-group">
              <input type="number" value="<%=user.getId() %>" name="organization_id" hidden/>
              <br />
              <br />
             
              <input
                type="text"
                class="form-control bg-dark"
                id="exampleInputName"
                aria-describedby="emailHelp"
                placeholder="Username"
                style="color: white;"
                name="organization_name"
                value="<%=user.getName() %>"
              />
              <br />
              <br />
              
              
                <input
                type="text"
                class="form-control bg-dark"
                id="exampleInputName"
                aria-describedby="emailHelp"
                placeholder="Email"
                style="color: white;"
                name="organization_email"
                value="<%= user.getEmail() %>"
              />
              <br />
              <br />
            <input type="submit" class="btn btn-outline-warning hover-effect" value="Submit">
            
          </form>
          
        </div>
        <a href="OTPSend" style="text-decoration: none; color:paleturquoise;">Change Password</a>
      </div>
    </div>
  </body>
</html>
