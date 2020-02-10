<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="controller.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% ArrayList<User> users= CRUD.getFriends();
	User currentUser = (User)request.getSession().getAttribute("user"); %>
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
    <title>Friends</title>
  </head>
  <body>
    <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
      <a class="navbar-brand" href="newsfeed.php">beTogether</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto" style="margin-left: 800px;">
          <li class="nav-item">
            <a class="nav-link" href="newsfeed.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="profiles.php">Profiles</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="friends.jsp">Friends</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Logout">Logout</a>
          </li>
        </ul>

        <form class="form-inline my-2 my-lg-0">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
            <a
              href="post_upload.php"
              style="text-decoration: none;color: rgb(0, 255, 64);"
              >Post</a
            >
          </button>
        </form>
      </div>
    </nav></div>
    <div class="container">
      <div class="row align-items-center">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
          <h3 style="margin-bottom: 40px; color:orange">
            <table><style>
table, th, td {
    border: 1px solid black;
  padding:5px;
}
</style>
      <th>Name</th>
      <th>Email</th>
      <th></th>
            <%if(users.isEmpty())
            	out.println("0 results");
            	else
            	{
            	for(User user:users){
            		if(user.getId() != currentUser.getId())
            		{%>
            		<tr>
            <td><a href='friend_profile.jsp?friend_id=<%= user.getId() %> && friend_name=<%=user.getName() %> && friend_email=<%=user.getEmail() %>' style='text-decoration: none;  color:orange'><%=user.getName() %></a></td>
            <td><a href='www.gmail.com' style='text-decoration: none;  color:orange'><%= user.getEmail() %></td>
            <td><a class='btn btn-outline-success' href='peer_chat.php?friend_id=$row[id]'>Send Email</a></td>
          </tr>
            		
            	<% }}%>
            	
            		
            	<%}%>
            </table>
          </h3>
        </div>
      </div>
    </div>
  </body>
</html>