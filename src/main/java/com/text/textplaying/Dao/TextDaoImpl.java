package com.text.textplaying.Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import com.text.textplaying.Authentic.Authentic;
import com.text.textplaying.persondb.persondb;

@Repository
public class TextDaoImpl implements TextDao {

	Authentic AuthObj = new Authentic();
	persondb Per = new persondb();

	@Override
	public String inserttext(String userPass, String fileName, String txtValue) {

		AuthObj.decodeUserPass(userPass);
		String user = AuthObj.getusername();
		String password = AuthObj.getpassword();
		if (Per.finduser(user) == 0)
			return "Username does not exist";

		String hashedPassword = Per.getpassword(user);
		System.out.println(password + "    " + hashedPassword);
		if (BCrypt.checkpw(password, hashedPassword)) {

			String FileCheck = fileName + ".txt";
			if (Per.checkfilename(FileCheck) == 1)
				return "Filename already exist . Please change the filename";

			String defaultPath = "C:\\Users\\SAURAV\\eclipse-workspace\\textplaying\\";

			File file = new File(fileName + ".txt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			FileWriter filewriter = null;
			try {
				filewriter = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				filewriter.write(txtValue);
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String path = defaultPath + fileName + ".txt";
			fileName += ".txt";

			try {
				Per.updatedb(user, fileName, path);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "user entry done";
		}

		else {
			return "Username password incorrect . Please enter the correct username password";
		}

	}

	@Override
	public List<String> getmyfiles(String userPass) {

		AuthObj.decodeUserPass(userPass);
		String user = AuthObj.getusername();
		String password = AuthObj.getpassword();
		if (Per.finduser(user) == 0)
			return new ArrayList<String>(Arrays.asList("Username does not exist"));

		String hashedPassword = Per.getpassword(user);

		if (BCrypt.checkpw(password, hashedPassword)) {
			return Per.getallfiles(user);
		} else {
			return new ArrayList<String>(Arrays.asList("Username and Password Mismatched"));
		}

	}

	@Override
	public String readfile(String userPass, String fileName) {

		AuthObj.decodeUserPass(userPass);
		String user = AuthObj.getusername();
		String password = AuthObj.getpassword();
		if (Per.finduser(user) == 0)
			return "Username does not exist";

		String hashedPassword = Per.getpassword(user);

		if (BCrypt.checkpw(password, hashedPassword)) {

			String Path;
			Path = Per.geturl(user, fileName);
			if (Path == null)
				return "No such file exists for the given user";

			File fileread = null;
			Scanner sc = null;
			try {
				fileread = new File(Path);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				sc = new Scanner(fileread);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String TxtVal = "";
			while (sc.hasNextLine()) {
				TxtVal = TxtVal.concat(sc.nextLine() + "\n");
			}

			return TxtVal;

		} else {
			return "Username password mismatched";
		}

	}

	@Override
	public String deletefile(String userPass, String fileName) {

		AuthObj.decodeUserPass(userPass);
		String user = AuthObj.getusername();
		String password = AuthObj.getpassword();
		if (Per.finduser(user) == 0)
			return "Username does not exist";

		String hashedPassword = Per.getpassword(user);

		if (BCrypt.checkpw(password, hashedPassword)) {

			if (Per.deletefile(user, fileName) == 1)
				return "File Deleted";
			else {
				return "File not found for the user";
			}
		}

		else {
			return "Username and password mismatched";
		}
	}

	@Override
	public String update(String userPass, String fileName, String txtValue) {

		AuthObj.decodeUserPass(userPass);
		String user = AuthObj.getusername();
		String password = AuthObj.getpassword();
		if (Per.finduser(user) == 0) {
			return "Username does not exist";
		}

		String hashedPassword = Per.getpassword(user);

		if (BCrypt.checkpw(password, hashedPassword)) {

			String Path;
			Path = Per.geturl(user, fileName);
			if (Path == null) {
				return "No such file exists for the given user to get updated";
			}

			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			writer.print("");
			writer.close();

			FileWriter F1 = null;
			try {
				F1 = new FileWriter(Path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				F1.write(txtValue);
				F1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "updated";
		}

		else {
			return "username password mismatched cannot update the file";
		}

	}

	@Override
	public String register(String userPass) {

		String[] UsrPasSplit = userPass.split(":");
		String user = UsrPasSplit[0], password = UsrPasSplit[1];
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

		if (Per.finduser(user) == 0) {
			Per.insertuserpass(user, hashedPassword);
			return "user entry done";
		} else {
			return "username already exist . Please try other username";
		}

	}

}
