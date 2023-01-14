package com.sk.deleteRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteRecord1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;
				
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			sc = new Scanner(System.in);
			
			System.out.println("Enter Salary Range: ");
			int salRange = sc.nextInt();
			int rowCount = st.executeUpdate("delete from emp where ESAL<"+salRange);
			System.out.println("No. of Rows Deleted: " + rowCount);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
				con.close();
				sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
