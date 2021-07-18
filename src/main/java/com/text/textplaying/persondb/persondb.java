package com.text.textplaying.persondb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.text.textplaying.connectionprovider.connectionProvider;

public class persondb {

	  public final JdbcTemplate jdbctemplate=null;
	  
	  public int updatedb(String User, String Filename, String Path) {
		  
		  String sql1 = "INSERT INTO person VALUES(?,?,?)";
		  
		  Connection con = connectionProvider.getconnection();
		  
		  PreparedStatement pstmt=null;
		  
		  try {
			    pstmt = con.prepareStatement(sql1);
		      }
		  catch (SQLException e) {
		    	e.printStackTrace();
		      }
		  
		  try {
			    pstmt.setString(1, User);
		      } 
		  catch (SQLException e) {
     			e.printStackTrace();
		      }
		 
		  try {
			   
			  pstmt.setString(2, Filename);
		      } 
		  catch (SQLException e1) {
			 e1.printStackTrace();
		      }
		      
		  try {
				pstmt.setString(3, Path);
			  } 
		  catch (SQLException e1) {
				e1.printStackTrace();
		      }
		  
		  try {
			 pstmt.executeUpdate();
		      } 
		  catch (SQLException e) {
			 e.printStackTrace();
		      }	
		  
		  return 1;
	  }
	  
	  public int checkFilename(String Fe) {
		  
		  String sql = "SELECT COUNT(filename)  FROM person where filename= ? "; int val=-1;
		  
          Connection con  = connectionProvider.getconnection();
		  
		  PreparedStatement pstmt = null;
		  
		  try {
			   pstmt = con.prepareStatement(sql);
		      } 
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  
		  try {
			   pstmt.setString(1,Fe);
		      }
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  
		 int cou=-1;
		  try {
		  ResultSet rs = pstmt.executeQuery();
		    while(rs.next())
		        cou=rs.getInt(1);
		      } 
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  return cou;
	  }
	  
	  
	  
	  public List<String>getallfiles(String user){
		  
		  final String sql= "SELECT FILENAME FROM person WHERE username = ? ";
		 
		  Connection con  = connectionProvider.getconnection();
		  
		  PreparedStatement pstmt = null;
		  
		  try {
			   pstmt = con.prepareStatement(sql);
		      } 
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  
		  try {
			   pstmt.setString(1, user);
		      }
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  List<String> lis = new ArrayList<String>(); String path; 
		  ResultSet rs=null;
		  try {
			  rs = pstmt.executeQuery();
		      } 
		  catch (SQLException e) {
			  e.printStackTrace();
		      }       
		  try {
			while (rs.next()) {               
			     path = rs.getString(1);        
			     lis.add(path);
			                                    
			  }
		      } catch (SQLException e) {
			 e.printStackTrace();
		      }
		  try {
			rs.close();
		      } 
		  catch (SQLException e) {
			e.printStackTrace();
		      }                                        
		  try {
			pstmt.close();
		      } 
		  catch (SQLException e) {
			e.printStackTrace();
		      }            
		  
		return lis;  
	  }
	  
	  
	  public String geturl(String username, String Fe) {
		  
		  String sql = "SELECT path FROM person WHERE username = ? AND filename = ? ";
		  
          Connection con  = connectionProvider.getconnection();
		  
		  PreparedStatement pstmt = null;
		  
		  try {
			   pstmt = con.prepareStatement(sql);
		      } 
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		 
		  try {
			   pstmt.setString(1,username);
			   pstmt.setString(2,Fe);
		      }
		  catch (SQLException e) {
			   e.printStackTrace();
		      }
		  
		    ResultSet rs = null;
		     try {
				rs =  pstmt.executeQuery();
			    }
		     catch (SQLException e) {
				e.printStackTrace();
			  }
		     
		    	 
		     String path=null;
		     try {
		     while (rs.next())                
			     path = rs.getString(1);
		         }
		     catch(Exception e) {
		    	 e.printStackTrace();
		     }
		  
		  return path;
	  }
	  
	  public int deletefile(String user, String Filename) {
		  
		 String sql = "DELETE FROM person WHERE username = ? AND filename = ? ";		 
		 
		 Connection con =  connectionProvider.getconnection();
		 
		 PreparedStatement pstmt=null;
		try {
			pstmt = con.prepareStatement(sql);
		    }
		catch (SQLException e1) {
			 e1.printStackTrace();
	    	}
		 
		try {
		  	pstmt.setString(1,user);
		  	pstmt.setString(2,Filename);
		    } 
		 catch (SQLException e) {
			  e.printStackTrace();
		    }
		int rv=-1;
		try {
		        rv= pstmt.executeUpdate();
		        pstmt.close();
		    } 
		 catch (SQLException e) {
			  e.printStackTrace();
		     }
		 
		 
	
		  return rv;
		  
	  }
	
}
