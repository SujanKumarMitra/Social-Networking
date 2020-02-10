package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CRUD;

public class RequestSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cause = request.getParameter("cause");
		int amount = Integer.parseInt(request.getParameter("amount"));
		User user =  (User)request.getSession().getAttribute("user");
		int res = CRUD.insertRequest(user.getId(),cause,amount);
		if(res == CRUD.POST_UPLOAD_SUCCESS)
			response.sendRedirect("newsfeed.jsp");
		else
			response.sendRedirect("upload_fail.html");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
