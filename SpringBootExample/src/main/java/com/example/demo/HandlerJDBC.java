package com.example.demo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*; 
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persons;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; 

@RestController 
public class HandlerJDBC{
	
	String firstname=null;
	String lastname=null;
	String contactno=null;
	String email=null;
	String gender=null;
	String aboutme=null;
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	
	public Statement getdbconnection() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Root@123");  
		stmt=con.createStatement(); 
	    return stmt;
		
	}
	
	@RequestMapping(value="/save_UserData", method= RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public void savedata(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		try{  
			ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode root = mapper.readTree(request.getReader());
            Persons mod = mapper.readValue(root.toString(), Persons.class);
            
			System.out.println(mod.getFirstName());
			HandlerJDBC dbconnection = new HandlerJDBC();
		    Statement stmt=dbconnection.getdbconnection();
		    String sql = "INSERT INTO Persons (FirstName,LastName,ContactNo,Email,Gender,AboutMe)" +
	                   "VALUES(" + root.get("FirstName")+ "," +  root.get("LastName")+","+  root.get("ContactNo") + ","+ root.get("Email")+","+ root.get("Gender")+","+  root.get("AboutMe")+ ")";
			System.out.println(sql);
		    stmt.executeUpdate(sql); 
			}catch(Exception e){ 
				//con.close();
				System.out.println(e);}  
		
		finally {
			
			if(con!=null) {
				con.close();
			}
		}
		
	}
	
	@RequestMapping(value="/get_UserData", method= RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
	public void getdata(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		try{  
			
			HandlerJDBC dbconnection = new HandlerJDBC();
		    Statement stmt=dbconnection.getdbconnection();
			rs=stmt.executeQuery("select * from Persons");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ " " + rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+ " " +rs.getString(7)); 
			}catch(Exception e){ 
				con.close();
				System.out.println(e);}  
		
	}

}
