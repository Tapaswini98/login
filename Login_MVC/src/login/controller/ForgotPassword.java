package login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.db.Student;
import login.model.StudentDao;
import login.service.StudentService;


@WebServlet("/login.controller.ForgotPassword")
public class ForgotPassword extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email= request.getParameter("email");
		
		Student s = new Student();
		s.setEmail(email);
		
		String db_password =StudentDao.checkEmailGetPassword(s);
		
		if(db_password == null)
		{
			response.sendRedirect("forgot.jsp?msg=Account does not exist..");
		}
		else
		{
			s.setPassword(db_password);
			StudentService.sendPassword(s);
			response.sendRedirect("login.jsp?msg=Password sent to Email");
		}
		
		
	}

}