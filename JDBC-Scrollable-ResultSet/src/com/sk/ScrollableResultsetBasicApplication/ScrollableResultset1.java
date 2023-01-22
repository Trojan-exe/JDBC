package com.sk.ScrollableResultsetBasicApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScrollableResultset1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = st.executeQuery("select * from emp");
			System.out.println("Employee Details in Forward Direction: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			while(rs.next()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
			}
			
			System.out.println("\nEmployee Details in Backward Direction: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			while(rs.previous()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
			}
			
			System.out.println("\nScrollable ResultSet Methods:");
			rs.afterLast();
			rs.previous();
			System.out.println(rs.getInt("ENO"));
			
			rs.beforeFirst();
			rs.next();
			System.out.println(rs.getInt("ENO"));
			
			rs.first();
			System.out.println(rs.getInt("ENO"));
			
			rs.last();
			System.out.println(rs.getInt("ENO"));
			
			rs.absolute(4);
			System.out.println(rs.getInt("ENO"));
			
			rs.absolute(-4);
			System.out.println(rs.getInt("ENO"));
			
			rs.first();
			rs.relative(3);
			System.out.println(rs.getInt("ENO"));
			
			rs.last();
			rs.relative(-3);
			System.out.println(rs.getInt("ENO"));
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
