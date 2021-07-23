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

	public String inserttext(String userPass, String fileName, String txtValue) {
		return this.textdao.inserttext(userPass, fileName, txtValue);
	}

	public List<String> getmyfiles(String userPass) {
		return this.textdao.getmyfiles(userPass);
	}

	public String readfile(String userPass, String fileName) {
		return this.textdao.readfile(userPass, fileName);
	}

	public String deletefile(String userPass, String fileName) {
		return this.textdao.deletefile(userPass, fileName);
	}

	public String update(String userPass, String fileName, String txtValue) {
		return this.textdao.update(userPass, fileName, txtValue);
	}

	public String register(String userPas) {
		return this.textdao.register(userPas);
	}
}
