package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CRUD;
import model.User;

/**
 * Servlet implementation class PasswordUpdate
 */
public class PasswordUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("new_password");
		User user = (User) request.getSession().getAttribute("user");
		int res = CRUD.updatePassword(user.getId(),password);
		if(res==CRUD.UPDATE_SUCCESS)
		{
			request.getSession().removeAttribute("OTP");
			response.sendRedirect("update_success.html");
		}
		else
		{
			response.sendRedirect("update_fail.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
