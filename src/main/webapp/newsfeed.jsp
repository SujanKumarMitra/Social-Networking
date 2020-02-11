<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="controller.*" %>
<%@page import="database.*" %>
<%
	User user = null;
	user = (User)session.getAttribute("user");
	if(user==null)
	{
		response.sendRedirect("login.html");
		return;
	}
	ArrayList<Post> posts = CRUD.fetchPosts();
	int hour = new Date().getHours();
	String message = null;
	if ( hour >= 5 && hour <= 11 ) {
	    message="Good Morning";
	} else if ( hour >= 12 && hour <= 18 ) {
	    message="Good Afternoon";
	} else if ( hour >= 19 || hour <= 4 ) {
	    message="Good Evening";
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
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="newsfeed.css" />
    <script
      src="https://kit.fontawesome.com/ec2985e005.js"
      crossorigin="anonymous"
    ></script>
    <title>News Feed</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
      <a class="navbar-brand" href="newsfeed.jsp">beTogether</a>
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
          <li class="nav-item active">
            <a class="nav-link" href="newsfeed.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="profiles.jsp">Profiles</a>
          </li>
         <li class="nav-item">
            <a class="nav-link" href="request.html">Request for fund</a>
          </li>
         <li class="nav-item">
            <a class="nav-link" href="show_request.jsp">Your Requests</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="friends.jsp">Friends</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Logout">Logout</a>
          </li>
        </ul>

        <form class="form-inline my-2 my-lg-0">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
            <a
              href="post_upload.jsp"
              style="text-decoration: none;color: rgb(0, 255, 64);"
              >Post</a
            >
          </button>
        </form>
      </div>
    </nav>
   
    <div class="alert alert-dark alert-dismissible fade show" role="alert">
        <%= message %> <strong><%= user.getName() %>!</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">x</span>
        </button>
      </div>

                                      <!-- End of the Navbar-->
                                      
    <div class="container">
      <div class="row">
        <!--Name Showing Division started-->
        <div
          class="col-2 text-center"
          style="background-color: rgb(22, 14, 11); "
        >
          <div class="sticky-top" style="height: 600px;">
            <img
              style="width: 100px;border-radius: 50%;margin-top: 20px;"
              src="<?php echo $_SESSION['image_source'];?>"
              alt=""
            />
            <h2
              style="font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;margin-top: 10px; color: #fff;"
            >
              <%= user.getName() %>
            </h2>
          </div>
        </div>
        <!--Name Showing Division end-->
        <!--Middle division started-->
        <div
          class="col-10"
          style="background-color: rgb(34, 27, 6);color: #fff;"
        >
          <!--This is the post part-->
          <div style="margin-top: 60px;">

			<%
			if(posts.isEmpty())
			{%>
				<h1>No Post. Be the first one to post anything.</h1>
			<%}
			else{
			%>
			<%
			for(Post post:posts)
			{%>
				<div style='margin-top: 60px;'>
					<div class='jumbotron bg-dark'>
						<img style='width: 100px; border-radius: 50%; margin-top: 20px;'
							src='$row2[ImageSource]' alt='' />
						<h1 class='display-4'><%=post.getName() %></h1> shared by
						<%= CRUD.getUserNameById(post.getUserId()) %>

						<p class='lead' style='width: 700px;'><%= post.getContent() %></p>

						<div class='row'>
							<div class='col'>
								<a href='Like?post_id=<%=post.getId() %>'
									class='fas fa-heart effect'
									style='font-size: 25px; margin-right: 50px;'>Like</a>
								<%=CRUD.getLikeFromPost(post.getId()) +" likes." %>
							</div>
							<div class='col'>
								<form action='Comment' method='POST'>
									<input type='number' name='post_id' value='<%=post.getId() %>' hidden>
									<div class='input-group mb-3'>
										<input type='text' class='form-control  bg-dark'
											placeholder='Comment' aria-label='Recipients username'
											aria-describedby='button-addon2' name='comment_input'
											required />
										<input type='submit'
											class='btn btn-outline-secondary input-group-append'
											type='button' id='button-addon2' value='Comment'>
									</div>
								</form>
							</div>
						</div>
						<a href='view_comments.jsp?post_id=<%=post.getId() %>'
							style='text-decoration: none; color: #fff;'>View all
							Comments</a>
						<br>
						<a href='view_likes.jsp?post_id=<%=post.getId() %>'
							style='text-decoration: none; color: #fff;'>View all likes</a>
					</div>
				</div>
					<%
						}
					%>
			<%} %>
					<!--Post part ends-->
          </div>
          <!--Friend list ends-->
        </div>
      </div>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
      integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
      integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
      integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
      crossorigin="anonymous"
    ></script>
  </body>
</html>