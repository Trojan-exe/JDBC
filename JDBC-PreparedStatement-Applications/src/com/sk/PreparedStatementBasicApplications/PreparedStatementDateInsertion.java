package com.sk.PreparedStatementBasicApplications;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class PreparedStatementDateInsertion {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			pst = con.prepareStatement("Insert into Student values(?,?,?,?)");
			
			pst.setString(1, "S-111");
			pst.setString(2, "aaa");
			
			java.sql.Date dob = java.sql.Date.valueOf("2000-08-04");
			pst.setDate(3, dob);
			
			Date d1 = new Date();
			long val = d1.getTime();
			java.sql.Date doj = new java.sql.Date(val);
			pst.setDate(4, doj);
			
			int rowCount = pst.executeUpdate();
			if(rowCount == 1) {
				System.out.println("Student inserted successfully");
			}else {
				System.out.println("Student not inserted succesfully");
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
