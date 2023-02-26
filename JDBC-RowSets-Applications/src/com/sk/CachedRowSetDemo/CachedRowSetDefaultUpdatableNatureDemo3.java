//CachedRowSet Default Updatable nature - Delete Operation.

package com.sk.CachedRowSetDemo;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetDefaultUpdatableNatureDemo3 {
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
				int eno = rowSet.getInt(1);
				float esal = rowSet.getFloat(3);
				if(esal > 10000) {
					rowSet.deleteRow();
					System.out.println("Employee " + eno + " Deleted Successfullyy!");
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
