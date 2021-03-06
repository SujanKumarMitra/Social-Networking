<%@page import="java.util.Map"%>
<%@page import="database.CRUD"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	User user = null;
	user =(User) session.getAttribute("user");
	if(user==null)
	{
		response.sendRedirect("login.html");
		return;
	}
	HashMap<String,String> comments = CRUD.getComments(Integer.parseInt(request.getParameter("post_id")));
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <title>Comments</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles." />

  <meta name="viewport"
    content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <link rel="stylesheet" media="screen" href="css/style1.css" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
  <link rel="stylesheet" href="./css/css/login.css" />
  <link rel="icon" href="icon.png" type="image/png" />
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a class="navbar-brand" href="#">beTogether</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse " id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto" style="margin-left: 800px;">
        <li class="nav-item">
          <a class="nav-link" href="newsfeed.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="profiles.jsp">Profiles</a>
        </li>
        <!--<li class="nav-item">
            <a class="nav-link" href="#">Group Chat</a>
          </li>-->
        <li class="nav-item">
          <a class="nav-link" href="friends.jsp">Friends</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Logout">Logout</a>
        </li>
      </ul>

      <form class="form-inline my-2 my-lg-0">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
          <a href="post_upload.php" style="text-decoration: none;color: rgb(0, 255, 64);">Post</a>
        </button>
      </form>
    </div>
  </nav>
  <!-- count particles 
<div class="count-particles">
  <span class="js-count-particles">--</span> particles
</div>
-->
  <!-- particles.js container -->
  <div id="particles-js">
    <div class="container to-center" style="background-color: rgba(0, 0, 0, 0.404);">
      <div class="row">
        <div class="col text-left align-to-center" style="margin-top: 100px">
          <%if(comments.isEmpty())
            {%>
          <h4 style='margin-bottom: 40px; color:rgb(17, 179, 201)'>
            No one commented this post.
          </h4>
          <%}else{
            	for(Map.Entry<String,String> comment:comments.entrySet())
            	{%>
          <h4 style='margin-bottom: 40px; color:rgb(17, 179, 201)'>
            <a href='#' style='text-decoration: none;  color:orange'><%=comment.getKey() %>
            </a>
            <p style='margin-top: 10px; font-size: 20px;'>
              <%=comment.getValue() %>
            </p>
          </h4>
          <%}
            }
            %>
        </div>
      </div>
    </div>
  </div>

  <!-- scripts -->
  <script src="particles.js"></script>
  <script src="js/app.js"></script>

  <!-- stats.js -->
  <script src="js/lib/stats.js"></script>
  <!--
    <script>
      var count_particles, stats, update;
      stats = new Stats();
      stats.setMode(0);
      stats.domElement.style.position = "absolute";
      stats.domElement.style.left = "0px";
      stats.domElement.style.top = "0px";
      document.body.appendChild(stats.domElement);
      count_particles = document.querySelector(".js-count-particles");
      update = function() {
        stats.begin();
        stats.end();
        if (
          window.pJSDom[0].pJS.particles &&
          window.pJSDom[0].pJS.particles.array
        ) {
          count_particles.innerText =
            window.pJSDom[0].pJS.particles.array.length;
        }
        requestAnimationFrame(update);
      };
      requestAnimationFrame(update);
    </script>
  -->
</body>

</html>