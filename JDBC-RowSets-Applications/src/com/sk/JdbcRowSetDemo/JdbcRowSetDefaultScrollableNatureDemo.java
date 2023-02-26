package com.sk.JdbcRowSetDemo;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetDefaultScrollableNatureDemo {
	public static void main(String[] args) {
		JdbcRowSet rowSet = null;
		
		try {
			rowSet = RowSetProvider.newFactory().createJdbcRowSet();
			rowSet.setUrl("jdbc:mysql://localhost:3306/Saurabh");
			rowSet.setUsername("root");
			rowSet.setPassword("root");
			
			rowSet.setCommand("select * from emp");
			rowSet.execute();
			
			System.out.println("Employee Details in Forward Direction: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------");
			while(rowSet.next()) {
				System.out.print(rowSet.getInt(1) + "\t");
				System.out.print(rowSet.getString(2) + "\t");
				System.out.print(rowSet.getFloat(3) + "\t");
				System.out.print(rowSet.getString(4) + "\n");
			}
			
			System.out.println("\nEmployee Details in Backward Direction: ");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------");
			while(rowSet.previous()) {
				System.out.print(rowSet.getInt(1) + "\t");
				System.out.print(rowSet.getString(2) + "\t");
				System.out.print(rowSet.getFloat(3) + "\t");
				System.out.print(rowSet.getString(4) + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rowSet.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
