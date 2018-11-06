package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 

@RestController  
public class RequestHandler {
	
	
	public String writerequest(Map<String, String> body) {
		
		String firstname=null;
		String lastname=null;
		String contactno=null;
		String gender=null;
		String email=null;
		String aboutme=null;
		
		try{
			firstname = body.get("FirstName");
			lastname = body.get("LastName");
			contactno = body.get("ContactNo");
			gender = body.get("Gender");
			email=body.get("Email");
			aboutme = body.get("AboutMe");
			String jsonformat = "{"+ '"' + "firstname" + '"' + ":" + '"' + firstname +'"' + "," +'"' + "lastname" + '"' + ":" + '"' + lastname +'"' + "," + '"' + "contactno" + '"' + ":" + '"' + contactno +'"' + "," +'"' + "email" + '"' + ":" + '"' + email +'"' + ","+ '"' + "gender" + '"' + ":" + '"' + gender +'"' + "," + '"' + "aboutme" + '"' + ":" + '"' + aboutme +'"' + "}";
			Random rand = new Random(); 
			int value = rand.nextInt(50); 
			FileWriter file = new FileWriter("D:\\"+firstname+".txt",true);
			//file.write("{" + '"' + value + '"' + ":" + '"' + body.toString()+ '"' + "}" + "," + System.getProperty( "line.separator" ));
			file.write("{" + '"' + value + '"' + ":" + "'" + jsonformat + "'" +"}");
			file.flush();
			System.out.println("Successfully Copied JSON Request to File...");
			System.out.println("\nJSON Request: " + body.toString());
			return true + "," + value;
		}
		catch(Exception E) {
			return false + ",";
		}
		
	}
		

	@RequestMapping(value = "/create", method = RequestMethod.POST)  
    public String create(@RequestBody Map<String, String> body){
		
		String firstname=null;
		boolean bool = false;  
		String write = null;
		
		
		RequestHandler obj1 = new RequestHandler();
		
		try {
			
		System.out.println("Create Request Landed" + body);
		if(!body.isEmpty()) {
	    firstname = body.get("FirstName");
		      File file = new File("D:\\"+firstname+".txt");
		      File file2 = file.getCanonicalFile();
		      if(file2.exists()) {
		    	  
		    	  write = obj1.writerequest(body);
		    	  String write1[] = write.split(",");
		    	 if(Boolean.valueOf(write1[0])) {
		    	  return write1[1];
		    	 }else {return "Request not copied to file.";}
		      }
		      
		      /*else if (!file2.exists()){*/
		      else {
		    	  file.createNewFile();
		    	   write = obj1.writerequest(body);
		    	  String write1[] = write.split(",");
		    	  if(Boolean.valueOf(write1[0])) {
		    		  System.out.println("File is created! and JSON Request has been copies in to file");
			    	  return write1[1];
			    	 }else {return "Request not copied to file.";}
		      }
		      
		      /*else{
		        System.out.println("File not created in the required path and Request is not copied.");
		        return "File not created in the required path and Request is not copied.";
		      }*/
		}
		else {
			System.out.println("Request body is" + body.isEmpty() + "empty");
			return "Request body is" + body.isEmpty() + "empty";
		}
	    	} catch (IOException e) {
		      e.printStackTrace();
		      return "Exception Catch Block";
		}  
    }  
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)  
    public String delete(@RequestBody Map<String, String> body) throws IOException, JSONException, ParseException{
		
		String firstname = body.get("FirstName");
		String edit = body.get("edit");
		String editvalue = body.get("edit");
		String a = null;
		String b = null;
		File f = new File("D:\\"+firstname+".txt");
        if (f.exists()){
            InputStream is = new FileInputStream("D:\\"+firstname+".txt");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            JSONObject json = new JSONObject(jsonTxt);   
            System.out.println(json);
            a = json.getString("15");
            System.out.println("1");
            System.out.println(a);  
            JSONParser parser = new JSONParser();
            System.out.println("2");
            JSONObject json1 = (JSONObject) parser.parse(a);
            System.out.println("3");
            b = json1.getString("edit");
            a.replaceAll(b, editvalue);
            FileWriter file = new FileWriter("D:\\"+firstname+".txt",true);
            file.write(a);
            file.flush();
            
        }
		return a;
	}
	
	@RequestMapping(value="/get_UsersData", method= RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public void getdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("Get User Data Method" + request.getParameter("FirstName"));
		response.getWriter().write("{\'firstName\':\'Rahul\',\'lastName\':\'John\', \'contactNo\':\'12345\'}");
		
	}
	
}
