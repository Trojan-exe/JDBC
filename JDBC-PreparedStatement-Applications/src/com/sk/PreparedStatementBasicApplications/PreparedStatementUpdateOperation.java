package com.sk.PreparedStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementUpdateOperation {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			pst = con.prepareStatement("update emp set ESAL = ESAL - ? WHERE ESAL < ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Employee Salary  : ");
			float amt = Float.parseFloat(br.readLine());
			System.out.println("Salary Range : ");
			float salRange = Float.parseFloat(br.readLine());
			
			pst.setFloat(1, amt);
			pst.setFloat(2, salRange);
			int rowCount = pst.executeUpdate();
			System.out.println("No of rows updated : " + rowCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
