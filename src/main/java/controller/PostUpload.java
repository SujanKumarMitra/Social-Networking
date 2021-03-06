package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.CRUD;
import model.User;
@MultipartConfig
public class PostUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		User user = (User) request.getSession().getAttribute("user");
		
		String path = getServletContext().getInitParameter("file_upload_path");
		path += "uploaded_files/";
		File file = new File(path);
		file.mkdir();
		path += "BeTogether/";
		file = new File(path);
		file.mkdir();
//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.mkdir());
		path += "posts/";
		file = new File(path);
		file.mkdir();
		Date date = new Date();
		@SuppressWarnings("deprecation")
		String str = path + date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds()+".png";
		@SuppressWarnings("deprecation")
		String databaseStr = "/uploaded_files/BeTogether/posts/"+date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds()+".png";
		for (Part part : request.getParts()) {
            part.write(str);
        }
		
		int result = CRUD.postUpload(heading ,content,user.getId(),databaseStr);
		if(result == CRUD.POST_UPLOAD_SUCCESS)
		{
			response.sendRedirect("newsfeed.jsp");
		}
		else
		{
			response.sendRedirect("upload_fail.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
