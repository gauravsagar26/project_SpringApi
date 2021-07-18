package com.text.textplaying.Dao;

import java.util.List;

public interface TextDao {

	   public String register(String userpass);
	   public String inserttext(String str, String Filename, String str1);
	   public List<String> getmyfiles(String UserPass);
	   public String readfile(String UserPass , String Filename);
	   public String deletefile(String user, String Filename);
	   public boolean  verify(String user, String password);
	   public String update(String userpass,String Filename,  String str);
	
}
