package com.text.textplaying.Authentic;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.text.textplaying.Dao.TextDaoImpl;

public class Authentic {

	  public Map<String,String> User = new HashMap<String,String>();
	  public String user=null , password=null;
	  
	  
	  public void decodeUserPass(String userPass) {
		  
		   String[] Arr = userPass.split(" ");
		   String ByteVal= Arr[1];
		   byte[] Barray= ByteVal.getBytes();
		   byte[] decodedBytes = Base64.getDecoder().decode(Barray);
		   String NewArr = new String(decodedBytes);
		   String[] UserVerify = NewArr.split(":");
		   user= UserVerify[0]; password=UserVerify[1];
		   System.out.println(user + "   " + password);
		   
	  }
	  
	  public String getusername() {
		  return user;
	  }
	  
	  public String getpassword() {
		  
		  return password;
	  }
	
}
