package com.sk.BatchUpdation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchUpdationWithPreparedStatement {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Saurabh", "root", "root");
			pst = con.prepareStatement("insert into emp values(?,?,?,?)");
			
			pst.setInt(1, 999);
			pst.setString(2, "iii");
			pst.setFloat(3, 12500);
			pst.setString(4, "pune");
			pst.addBatch();
			
			pst.setInt(1, 1000);
			pst.setString(2, "jjj");
			pst.setFloat(3, 13500);
			pst.setString(4, "pune");
			pst.addBatch();
			
			int[] rowCounts = pst.executeBatch();
			
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
