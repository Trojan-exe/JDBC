package com.sk.UpdatableResultSetApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetDeleteOperation {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp");
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter ENO to be deleted : ");
			int eno = Integer.parseInt(br.readLine());
					
			while(rs.next()) {
				if(rs.getInt("ENO") == eno) {
					rs.deleteRow();
					System.out.println("Employee Deleted Succesfully!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
