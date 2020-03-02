package database;

import java.sql.Connection;
import java.sql.DriverManager;

public interface Properties {
	final String url = "jdbc:mysql://localhost:3306/be_together";
	final String userName = "root";
	final String password = "root";
	public static Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =  DriverManager.getConnection(url, userName, password);
			return connection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
