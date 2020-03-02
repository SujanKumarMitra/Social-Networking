<%@page import="model.*" %>
<%
User user = (User)request.getSession().getAttribute("user");
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
    <title>Upload</title>
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
            <a class="nav-link" href="newsfeed.php">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="profiles.php">Profiles</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Group Chat</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="friends.php">Friends</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Logout">Logout</a>
          </li>
        </ul>
      </div>
    </nav></div>
    <div class="container">
      <div class="row align-items-center" style="margin-top: 100px">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
          <h1 style="margin-bottom: 40px; color:orange">
            Post :
          </h1>
          <form action="PostUpload" method="POST" enctype="multipart/form-data">
            <div class="form-group">
             
              <input
                type="text"
                class="form-control bg-dark"
                id="exampleInputName"
                aria-describedby="emailHelp"
                placeholder="Heading"
                style="color: white;"
                name="heading"
              />
              <br />
              <br />
              
              <input type="text" class="form-control  bg-dark" rows="5" id="comment "placeholder="Content"
              style="color: white;" name="content">
                
              <br />
              <br />
              <input type="file" class = "form-control bg dark" name ="file"> 
              <br/>
              <br/>
            <input type="submit" class="btn btn-outline-warning hover-effect" value="Post it">
            
          </form>
          
        </div>
        
      </div>
    </div>
  </body>
</html>
