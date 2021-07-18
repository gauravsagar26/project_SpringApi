package com.text.textplaying.connectionprovider;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionProvider {

	 private static Connection con;
	  
	  public static Connection  getconnection()
	  {
		
		  try {
		  if(con==null)
		  {
			  
				Class.forName("org.postgresql.Driver");
			 		  
			    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test2","postgres","Gauravsagar@26");
			    
		  }
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }
		  
		  
		  return con;
	  }
	
	
}

