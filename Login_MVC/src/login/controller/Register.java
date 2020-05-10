package login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.db.Student;
import login.model.StudentDao;

@WebServlet("/login.controller.Register")
public class Register extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Student s =new Student();
		s.setUsername(request.getParameter("username"));
		s.setPassword(request.getParameter("password"));
		s.setEmail(request.getParameter("email"));
		s.setPhone(request.getParameter("phone"));
		s.setRegdno(request.getParameter("regdno"));
		s.setCollege(request.getParameter("college"));
		
		int status =StudentDao.doRegister(s);
		
		if(status >0)
		{
			response.sendRedirect("register.jsp?msg1=Register Success");
		}
		else
		{
			response.sendRedirect("register.jsp?msg2=Error in Registration");
		}	
		
		
	}

}
