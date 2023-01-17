package com.sk.readEmployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeReadService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public EmployeeReadService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee read(int eno)
	{
		Employee emp = null;
		try {
			rs = st.executeQuery("select * from emp where ENO="+eno);
			if(rs.next())
			{
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
