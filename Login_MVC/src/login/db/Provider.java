package login.db;
import java.sql.*;
public class Provider 
{
	static Connection con = null;
  public static Connection getOracleConnection() 
  {
	
	try
	{
		if(con==null)
		{
		Class.forName("oracle.jdbc.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "lit");
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return con;
}
  
}
  


