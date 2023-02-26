package com.sk.DefaultDatabaseConnectionPooling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class OracleDBConnectionPooling {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			OracleDataSource ds = new OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUser("System");
			ds.setPassword("root");
			
			con = ds.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from emp");
			
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("-------------------------------");
			while(rs.next()) {
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
