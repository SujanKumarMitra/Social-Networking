package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CRUD;
import model.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		User user = CRUD.verifyExecutiveLogin(email,password);
		if(user == null)
		{
			response.sendRedirect("login_fail.html");
			
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("newsfeed.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
