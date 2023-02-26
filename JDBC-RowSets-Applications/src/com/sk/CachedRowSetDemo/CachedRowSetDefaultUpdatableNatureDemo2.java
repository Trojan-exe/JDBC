//CachedRowSet Default Updatable nature - Update Operation.
package com.sk.CachedRowSetDemo;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetDefaultUpdatableNatureDemo2 {
	public static void main(String[] args) {
		Connection con = null;
		CachedRowSet rowSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Saurabh", "root", "root");
			con.setAutoCommit(false);
			
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
			rowSet.setCommand("select * from emp");
			rowSet.execute(con);
			
			while(rowSet.next()) {
				float esal = rowSet.getFloat(3);
				if(esal < 15000) {
					rowSet.updateFloat(3, esal+500);
					rowSet.updateRow();
					System.out.println("Employee " + rowSet.getInt(1) + " updated Successfully!");
				}
			}
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
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
