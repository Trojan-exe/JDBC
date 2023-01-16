package com.sk.loginForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	String status = "";
	
	public UserService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String checkLogin(String uname, String upwd) {
		try {
			rs = st.executeQuery("select * from reg_Users where UNAME = '"+uname+"' and UPWD = '" + upwd + "'");
			boolean b = rs.next();
			if(b == true) {
				status = "User Login Success";
			}else {
				status = "User Login Failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "User Login Failure";
		}
		return status;
	}
}
