package com.sk.createEmployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeWriteService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public EmployeeWriteService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String add(int eno, String ename, Float esal, String eaddr) {
		String status = "";
		try {
			rs = st.executeQuery("select * from emp where ENO=" + eno);
			boolean b = rs.next();
			if(b == true)
				status = "Employee with ID " + eno + " already exists";
			else {
				int rowCount = st.executeUpdate("insert into emp values(" + eno + ",'" + ename + "'," + esal + ",'" + eaddr + "')");
				if(rowCount == 1)
					status = "Employee Created Successfully!";
				else
					status= "Employee not created!!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "Employee not created!!!";
		}
		return status;
	}
}
