package com.sk.scrollableResultsetGUIApplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlayerService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	boolean state;
	
	public PlayerService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployeeDetails(String label) {
		Employee emp = null;
		state = false;
		try {
			if(label.equals("First")) {
				state = rs.first();
			}if(label.equals("Last")) {
				state = rs.last();
			}if(label.equals("Next")) {
				state = rs.next();
			}if(label.equals("Previous")) {
				state = rs.previous();
			}
			if(state == false)
			{
				emp = null;
			}
			else {
				emp = new Employee();
				emp.setENO(rs.getInt("ENO"));
				emp.setENAME(rs.getString("ENAME"));
				emp.setESAL(rs.getFloat("ESAL"));
				emp.setEADDR(rs.getString("EADDR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
}
