<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.stream.DoubleStream"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="controller.*" %>
<%@page import="database.*" %>
<%@page import="java.sql.*" %>
<%@ page import="model.*" %>
<% User user = (User)request.getSession().getAttribute("user");
   HashMap<String,Integer> result = CRUD.getRequests(user.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Control page</title>
</head>
<body>
    <table class="table table-dark container" style="margin-top: 25px;">
        <thead class="bg-warning">
          <tr>
            <th scope="col">Cause</th>
            <th scope="col">Amount</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
        <% if(result.isEmpty()){ %>
        	0 results.
        	<%}else{
        		for(Map.Entry<String,Integer>  res:result.entrySet())
        		{%>
        			<tr>
			        <td><%=res.getKey() %></td>
			        <td><%=res.getValue() %></td>
			        <td>Verified</td>
			      </tr>
        		
        		<%}}%>
        </tbody>
      </table>
</body>
</html>