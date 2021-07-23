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

	public final JdbcTemplate jdbctemplate = null;
	Connection con = connectionProvider.getconnection();

	public int finduser(String user) {

		String sql = "SELECT COUNT(username) FROM personverify WHERE username = ? ";
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println(user);
		ResultSet rs = null;
		int rv = -1;
		try {
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next())
				rv = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rv;

	}

	public String getpassword(String user) {

		String sql = "SELECT password FROM personverify WHERE username = ? ";
		PreparedStatement pstmt = null;
		String pas = null;

		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ResultSet rs = null;
		int rv = 0;

		try {
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next())
				pas = rs.getString(1);
			System.out.println(pas);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pas;

	}

	public void insertuserpass(String username, String password) {

		String sql = "INSERT INTO personverify VALUES(? , ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int updatedb(String user, String fileName, String path) {

		String sql1 = "INSERT INTO person VALUES(?,?,?)";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.setString(1, user);
			pstmt.setString(2, fileName);
			pstmt.setString(3, path);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
	}

	public int checkfilename(String fileName) {

		String sql = "SELECT COUNT(filename)  FROM person where filename= ? ";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.setString(1, fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int cou = -1;
		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				cou = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cou;
	}

	public List<String> getallfiles(String userName) {

		final String sql = "SELECT FILENAME FROM person WHERE username = ? ";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.setString(1, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> lis = new ArrayList<String>();
		String path;
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
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
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lis;
	}

	public String geturl(String username, String fileName) {

		String sql = "SELECT path FROM person WHERE username = ? AND filename = ? ";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt.setString(1, username);
			pstmt.setString(2, fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String path = null;
		try {
			while (rs.next())
				path = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public int deletefile(String user, String fileName) {

		String sql = "DELETE FROM person WHERE username = ? AND filename = ? ";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			pstmt.setString(1, user);
			pstmt.setString(2, fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int rv = -1;
		try {
			rv = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rv;

	}

}
