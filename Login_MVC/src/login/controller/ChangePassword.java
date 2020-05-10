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


@WebServlet("/login.controller.ChangePassword")
public class ChangePassword extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String opass = request.getParameter("opass");
		String npass = request.getParameter("npass");
		String cpass = request.getParameter("cpass");
		
		Student s = new Student();
		HttpSession session = request.getSession();
		s.setEmail((String)session.getAttribute("email"));
		s.setPassword(opass);
		int status =StudentDao.validate(s);
		if(status>0)
		{
			if(npass.equals(cpass))
			{
				s.setPassword(npass);
				int r = StudentDao.updatePassword(s);
				
				if (r>0)
					
				  response.sendRedirect("changePassword.jsp?msg=Password changed");
					
				
				else
					response.sendRedirect("changePassword.jsp?msg=Error");
				
				
			}
			else
					response.sendRedirect("changePassword.jsp?msg=New Password and Confirm password mismatch");
			
				
		}
		else
				response.sendRedirect("changePasssword.jsp?msg=Current Password invalid");
			
			
	}
		
		
		
}


