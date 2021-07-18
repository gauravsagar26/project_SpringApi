package com.text.textplaying.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.text.textplaying.Service.TextService;

@RequestMapping("/api/v2")
@RestController
public class FileApi {
    
    private final TextService textService;
    
    
    
    
	@Autowired
	public FileApi(TextService textService) {
		this.textService = textService;
	}
	

	@PostMapping("/register")
	public String register(@RequestBody String userpass) {
		
		return this.textService.register(userpass);
	}
	
	
	@PostMapping("/text/{Filename}")
	public String inserttext(@RequestHeader(value="Authorization") String UserPass, @PathVariable String Filename, @RequestBody String TxtValue)
	{
		 
		return this.textService.inserttext(UserPass,Filename, TxtValue);
	}
	

	@PostMapping("/text/update/{Filename}")
    public String update(@RequestHeader(value="Authorization") String user, @PathVariable  String Filename, @RequestBody String str)	
    {
		
	  return this.textService.update(user, Filename , str);	
    }
	
	@GetMapping("/text")
	public List<String> getmyfiles(@RequestHeader(value="Authorization") String UserPass)
	{
	  return this.textService.getmyfiles(UserPass);
		
	}
	
	@GetMapping("/text/{Filename}")
	public String readfile(@RequestHeader(value="Authorization") String UserPass , @PathVariable String Filename)
	{
	  return this.textService.readfile(UserPass, Filename);
		
	}

	
	@DeleteMapping("/text/{Filename}")
	public String deletefile(@RequestHeader(value="Authorization") String user, @PathVariable String Filename)
	{
		
       return  this.textService.deletefile(user,Filename);		
	}
	  
	  
	 
	
	
}






