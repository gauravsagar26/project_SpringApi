package com.text.textplaying.Dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.text.textplaying.persondb.persondb;


@Repository
public class TextDaoImpl implements TextDao {

    Map<String,String> User = new HashMap<String,String>();
   
	  

	@Override
	public String inserttext(String UserPass, String Filename, String Txtvalue) {
		
	 
	   String[] Arr = UserPass.split(" ");
	   String ByteVal= Arr[1];
	   byte[] Barray= ByteVal.getBytes();
	   byte[] decodedBytes = Base64.getDecoder().decode(Barray);
	   String NewArr = new String(decodedBytes);
	   String[] UserVerify = NewArr.split(":");
	   String user= UserVerify[0], password=UserVerify[1];
       if(!User.containsKey(user))return"Please register before inserting the details";
       
       if(User.containsKey(user) && User.get(user).contains(password)) {
          
    	  String FileCheck = Filename+".txt";
          persondb Per = new persondb();
          if(Per.checkFilename(FileCheck)==1)return "Filename already exist . Please change the filename";
        
        
          String defpath="C:\\Users\\SAURAV\\eclipse-workspace\\textplaying\\";
         
          File F1 = new File(Filename+".txt");
          try {
			    F1.createNewFile();
		      } 
          catch (IOException e) {
			    e.printStackTrace();
		      }
          
          
          FileWriter fw=null;
          try {
			    fw = new FileWriter(F1);
		      } 
          catch (IOException e) {
			    e.printStackTrace();
		      }
          
          try {
			    fw.write(Txtvalue);
			    fw.close();
		      } 
          catch (IOException e) {
			    e.printStackTrace();
		      }
          
       
          
        String path= defpath+Filename+".txt";
        Filename+=".txt";
        persondb Pobj = new persondb();
        try{
        	Pobj.updatedb(user, Filename,  path);
           }
        catch(Exception e) {
        	e.printStackTrace();
           }
        
        return "user entry done"; 
       }
       
       else
    	   return "username and password mismatched. Please enter the correct username and password ";
	}

	@Override
	public List<String> getmyfiles(String UserPass) {
		 
		   String[] Arr = UserPass.split(" ");
		   String ByteVal= Arr[1];
		   byte[] Barray= ByteVal.getBytes();
		   byte[] decodedBytes = Base64.getDecoder().decode(Barray);
		   String NewArr = new String(decodedBytes);
		   String[] UserVerify = NewArr.split(":");
		   String user= UserVerify[0], password=UserVerify[1];
		 
     		   	   
	       if(!User.containsKey(user)) return new ArrayList<String>(Arrays.asList("Username incorrect"));

		 
	       if(User.containsKey(user) && User.get(user).contains(password)) {
		   persondb P = new persondb();
		   return P.getallfiles(user);
	       }
	       else {
	       return new ArrayList<String>(Arrays.asList("Username and Password Mismatched"));
	       }
		
		  
	}
	
	
	@Override
	public String readfile(String UserPass, String Filename) {
		
		   String[] Arr = UserPass.split(" ");
		   String ByteVal= Arr[1];
		   byte[] Barray= ByteVal.getBytes();
		   byte[] decodedBytes = Base64.getDecoder().decode(Barray);
		   String NewArr = new String(decodedBytes);
		   String[] UserVerify = NewArr.split(":");
		   String user= UserVerify[0], password=UserVerify[1];
		 
		   if(!User.containsKey(user)) return "Please register and enter the files details before reading";
		   
		   if(User.containsKey(user) && User.get(user).contains(password)) {
			   
			   String Path;
			   persondb Per = new persondb();
			   Path= Per.geturl(user,Filename);
			   if(Path==null) return "No such file exists for the given user";
			   
			   File Fr= null;Scanner sc=null;
			   try {
				Fr = new File(Path);
			       }
			   catch (Exception e) {
				    e.printStackTrace();
			       }
			   
			   try {
				    sc = new Scanner(Fr);
			       }
			   catch (FileNotFoundException e) {
			    	e.printStackTrace();
		       	   }
			   String TxtVal="";
			   while(sc.hasNextLine()) {
				     TxtVal = TxtVal.concat(sc.nextLine() + "\n");
			       }
			  
			   return TxtVal;
			   
		   }
		   else
		       return "Username password mismatched";

	}
	
	

	@Override
	public String deletefile(String user, String Filename) {
		
		   String[] arr = user.split(" ");
		   String byteval= arr[1];
		   byte[] b= byteval.getBytes();
		   byte[] decodedBytes = Base64.getDecoder().decode(b);
		   String newarr = new String(decodedBytes);
		   String[] userverify = newarr.split(":");
		   String usr= userverify[0], password=userverify[1];
		
		 
		   if(!User.containsKey(usr))return "User name Invalid";
		   
		   if(User.containsKey(usr) && User.get(usr).equals(password)) {
		 
		   persondb Pobj = new persondb();
		   if(Pobj.deletefile(usr,Filename)==1) return "File Deleted";
		   else return "File not found for the user";
		
		  }
		   
		   else 
			   return "Username and password mismatched";
	}

	@Override
	public boolean verify(String user, String password) {
	    
		if(User.get(user).contains(password))
			return true;
		else
			return false;
	}

	@Override
	public String update(String user, String Filename , String str) {
		
		   String[] arr = user.split(" ");
		   String byteval= arr[1];
		   byte[] b= byteval.getBytes();
		   byte[] decodedBytes = Base64.getDecoder().decode(b);
		   String newarr = new String(decodedBytes);
		   String[] userverify = newarr.split(":");
		   String usr= userverify[0], password=userverify[1];

		
    		if(User.containsKey(usr) && User.get(usr).equals(password)) {
			
			
			 String Path;
			 persondb Per = new persondb();
			 Path= Per.geturl(usr,Filename);
			 if(Path==null) return "No such file exists for the given user to get updated";
   

			
			PrintWriter writer=null;
			try {
			   	writer = new PrintWriter(Filename);
			    }
			catch (FileNotFoundException e) {
			 	 e.printStackTrace();
			    }
			writer.print("");
			writer.close();
			
			FileWriter F1=null;
			try {
				  F1 = new FileWriter(Path);  
			    } 
			catch (IOException e) {
				e.printStackTrace();
			    }
			try {
			      F1.write(str);
			      F1.close();
			    }
			catch(Exception e) 
			    {e.printStackTrace();}
			

			return "updated";
		}
	         
	         return "username password mismatched cannot update the file"; 
	         
	
	}

	@Override
	public String register(String UserPass) {
		
		 String[] UsrPasSplit = UserPass.split(":");
		 String user=UsrPasSplit[0] , password=UsrPasSplit[1];
		 if(User.containsKey(user))return"user name already exist";
	       
         User.put(user,password);
       
         return "user entry done";
	}


	
	  
	  
	
}
