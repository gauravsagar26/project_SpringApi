package com.text.textplaying.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.text.textplaying.Dao.TextDao;

@Service
public class TextService {

	   
	  private final TextDao textdao;

    @Autowired
	public TextService(TextDao textdao) {
		this.textdao = textdao;
	}
	  
	
	 public String inserttext(String UserPass, String Filename, String Txtvalue)
	 {
		 
		 return this.textdao.inserttext(UserPass,Filename,Txtvalue);
	 }
	 
	 
	 public List<String> getmyfiles(String UserPass)
	 {
		 
		 return this.textdao.getmyfiles(UserPass);
	 }
	 
	 public String readfile(String UserPass , String Filename)
		{
		  return this.textdao.readfile(UserPass, Filename);
			
		}

	  
	 
   	 public String deletefile(String user, String Filename)
	 {
		 
		 return this.textdao.deletefile(user,Filename);
	 }
	 
	 public String update(String user, String Filename , String str)
	 {
		 
		 return this.textdao.update(user, Filename , str);
				 
	 }
	 
	 public String register(String usrpas) {
		 
		 return this.textdao.register(usrpas);
	 }
}
