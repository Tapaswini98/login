package login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.db.Student;
import login.model.StudentDao;


@WebServlet("/login.controller.Login")
public class Login extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email=request.getParameter("email");
		String password =request.getParameter("password");
		
		Student s =new Student();
		s.setEmail(email);
		s.setPassword(password);
		
		int status =StudentDao.validate(s);
		
		if(status >0)
		{
		   HttpSession session =request.getSession();
		   session.setAttribute("email", email);
		   response.sendRedirect("welcome.jsp?msg=Login Success");
		   
		}
		else
			response.sendRedirect("login.jsp?msg=Invalid Email or Password");
	}

}
