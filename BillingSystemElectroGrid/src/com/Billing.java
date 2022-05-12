package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Billing {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/egsystem", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBilling(String bAcc, String bName, String bAddress, String bMonth, String bUnit, String bUnitP, String bAmount)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into billing(`bID`,`bAcc`,`bName`,`bAddress`,`bMonth`,`bUnit`,`bUnitP`,`bAmount`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, bAcc);
			 preparedStmt.setString(3, bName);
			 preparedStmt.setString(4, bAddress);
			 preparedStmt.setString(5, bMonth);
			 preparedStmt.setString(6, bUnit);
			 preparedStmt.setString(7, bUnitP);
			 preparedStmt.setString(8, bAmount);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newBilling = readBilling(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the billing.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readBilling()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Account No</th><th>Customer Name</th><th>Customer Address</th><th>Month</th><th>Used unit</th><th>Unit Price</th><th>Amount</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from billing";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String bID = Integer.toString(rs.getInt("bID"));
				 String bAcc = rs.getString("bAcc");
				 String bName = rs.getString("bName");
				 String bAddress = rs.getString("bAddress");
				 String bMonth = rs.getString("bMonth");
				 String bUnit = rs.getString("bUnit");
				 String bUnitP = rs.getString("bUnitP");
				 String bAmount = rs.getString("bAmount");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidBillingIDUpdate\' name=\'hidBillingIDUpdate\' type=\'hidden\' value=\'" + bID + "'>" 
							+ bAcc + "</td>"; 
				output += "<td>" + bName + "</td>";
				output += "<td>" + bAddress + "</td>";
				output += "<td>" + bMonth + "</td>";
				output += "<td>" + bUnit + "</td>";
				output += "<td>" + bUnitP + "</td>";
				output += "<td>" + bAmount + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='" + bID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the billing.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateBilling(String bID, String bAcc, String bName, String bAddress, String bMonth, String bUnit, String bUnitP, String bAmount)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE billing SET bAcc=?,bName=?,bAddress=?,bMonth=?,bUnit=?,bUnitP=?,bAmount=?"  + "WHERE bID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, bAcc);
			 preparedStmt.setString(2, bName);
			 preparedStmt.setString(3, bAddress);
			 preparedStmt.setString(4, bMonth);
			 preparedStmt.setString(5, bUnit);
			 preparedStmt.setString(6, bUnitP);
			 preparedStmt.setString(7, bAmount);
			 preparedStmt.setInt(8, Integer.parseInt(bID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newBilling = readBilling();    
			output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the billing.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteBilling(String bID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from billing where bID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(bID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newBilling = readBilling();    
			output = "{\"status\":\"success\", \"data\": \"" +  newBilling + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the billing.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
