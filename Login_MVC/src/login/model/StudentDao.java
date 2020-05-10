package login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import login.db.Provider;
import login.db.Student;

public class StudentDao {

	public static int doRegister(Student s) 
	{
		int status =0;
		try 
		{
			Connection con =Provider.getOracleConnection();
			String sql="insert into student values(?,?,?,?,?,?)";
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setString(1,s.getUsername());
			pst.setString(2, s.getPassword());
			pst.setString(3, s.getEmail());
			pst.setString(4, s.getPhone());
			pst.setString(5, s.getRegdno());
			pst.setString(6, s.getCollege());
			status =pst.executeUpdate();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public static int validate(Student s) 
	{
		
		int status =0;
		try 
		{
			Connection con =Provider.getOracleConnection();
			String sql="select * from student where email =? and password =?";
			PreparedStatement pst= con.prepareStatement(sql);
			
			pst.setString(1, s.getEmail());
			pst.setString(2, s.getPassword());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				status =1;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public static String checkEmailGetPassword(Student s) 
	{
		String pass =null;
		try 
		{
			Connection con =Provider.getOracleConnection();
			String sql="select password from student where email =?";
			PreparedStatement pst= con.prepareStatement(sql);
			
			pst.setString(1, s.getEmail());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			pass = rs.getString(1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return pass;
	}

	public static int updatePassword(Student s) 
	{
		int status = 0;
		try 
		{
			Connection con = Provider.getOracleConnection();
			String sql = "update student set password=? where email=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, s.getPassword());
			pst.setString(2, s.getEmail());
			status = pst.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}

}
