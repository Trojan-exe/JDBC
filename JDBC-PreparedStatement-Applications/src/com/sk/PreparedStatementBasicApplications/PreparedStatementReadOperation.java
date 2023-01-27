// Prepared Statement is supported by all drivers of all database vendors.

package com.sk.PreparedStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementReadOperation {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			pst = con.prepareStatement("select * from emp where ESAL < ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			
			System.out.println("Salary Range  : ");
			float salRange = Float.parseFloat(br.readLine());
				
			pst.setFloat(1, salRange);
			rs = pst.executeQuery();
			
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("-------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.println(rs.getString("EADDR"));
			}
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
