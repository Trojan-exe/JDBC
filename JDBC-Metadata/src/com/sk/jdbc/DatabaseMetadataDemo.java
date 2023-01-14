package com.sk.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DatabaseMetadataDemo {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
					
			DatabaseMetaData md = con.getMetaData();
			System.out.println(md.getDatabaseProductName());
			System.out.println(md.getDatabaseProductVersion());
			System.out.println(md.getDriverMinorVersion());
			System.out.println(md.getDriverMajorVersion());
			System.out.println(md.getSQLKeywords());
			System.out.println(md.getStringFunctions());
			System.out.println(md.getNumericFunctions());
			System.out.println(md.supportsBatchUpdates());
			System.out.println(md.supportsStoredProcedures());
			
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
