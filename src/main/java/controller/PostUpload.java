package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CRUD;

public class PostUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		User user = (User) request.getSession().getAttribute("user");
		int result = CRUD.postUpload(heading ,content,user.getId());
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
