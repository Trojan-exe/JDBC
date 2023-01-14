package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ResultSetMetadataDemo1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp");
			
			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			System.out.println("No. of Columns: " + colCount);
			
			for (int i = 1; i <= colCount; i++) {
				System.out.println("Column Name : " + md.getColumnName(i));
				System.out.println("Column Type : " + md.getColumnTypeName(i));
				System.out.println("Column Size : " + md.getColumnDisplaySize(i));
				System.out.println("-----------------------------------");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
