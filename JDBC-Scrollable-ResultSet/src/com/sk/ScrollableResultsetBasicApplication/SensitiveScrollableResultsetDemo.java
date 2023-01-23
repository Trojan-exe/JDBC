package com.sk.ScrollableResultsetBasicApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SensitiveScrollableResultsetDemo {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = st.executeQuery("select * from emp");
			System.out.println("Employee Details Before Modification: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
			}
			
			System.in.read();
			rs.beforeFirst();
			
			System.out.println("\nEmployee Details After Modification: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------------------");
			while(rs.next()) {
				rs.refreshRow();
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
