package com.sk.BatchUpdation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchUpdationWithStatement {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Saurabh", "root", "root");
			st = con.createStatement();
			
			st.addBatch("insert into emp values(888, 'hhh', 11000, 'pune')");
			st.addBatch("update emp set ESAL = ESAL + 500 where ESAL < 20000");
			st.addBatch("delete from emp where ENO = 111");
			int[] rowCounts = st.executeBatch();
			
			for(int rc : rowCounts) {
				System.out.println("Records Manipulated: " + rc);
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
