package ua.prettl.downloading;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.prettl.downloading.model.Employee;

public class Application {

	public static void main(String[] args) {
		// Create a variable for the connection string.  
	      String connectionUrl = "jdbc:sqlserver://10.49.0.9:1433;" +  
	         "databaseName=StopNet4_3;user=sa;password=Prettl!@#4";  

	      // Declare the JDBC objects.  
	      Connection con = null;  
	      Statement stmt = null;  
	      ResultSet rs = null;  

	      List<Employee> employees = new ArrayList<>();
	      
	      try {  
	         // Establish the connection.  
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	         con = DriverManager.getConnection(connectionUrl);  

	         // Create and execute an SQL statement that returns some data.  
	         String SQL = "SELECT TOP 10 * FROM dbo.vwAccountEmployees";  
	         stmt = con.createStatement();  
	         rs = stmt.executeQuery(SQL);  

	         
	         
	         // Iterate through the data in the result set and display it.  
	         while (rs.next()) {  
	        	 
	        	employees.add(new Employee(
	        			rs.getString(Employee.COL_AUTHORIZATION_NAME),
	        			rs.getString(Employee.COL_EMPLOYEE_NUMBER),
	        			rs.getString(Employee.COL_SURNAME), //surname
	        			rs.getString(Employee.COL_NAME), //name
	        			rs.getString(Employee.COL_MIDDLE_NAME))); //middleName
	        	 
	         }  
	      }  

	      // Handle any errors that may have occurred.  
	      catch (Exception e) {  
	         e.printStackTrace();  
	      }  
	      finally {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	      } 
	      
	      System.out.println(employees);

	}

}
