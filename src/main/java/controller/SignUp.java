package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.CRUD;

/**
 * Servlet implementation class SignUp
 */
@MultipartConfig
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("organization_name");
		String email = request.getParameter("organization_email");
		String pass = request.getParameter("organization_password");
		String path = getServletContext().getInitParameter("file_upload_path");
		path += "uploaded_files/";
		File file = new File(path);
		file.mkdir();
		path += "BeTogether/";
		file = new File(path);
		file.mkdir();
		path += "profile_photos/";
		file = new File(path);
		file.mkdir();
		for (Part part : request.getParts()) {
            part.write(path +email+".png");
        }
		int res = CRUD.createUser(userName, email, pass,"/uploaded_files/BeTogether/profile_photos/"+email+".png");
		if(res==CRUD.USER_CREATED)
		{
			response.sendRedirect("signup_success.html");
		}
		else
		{
			response.sendRedirect("signup_fail.html");
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
