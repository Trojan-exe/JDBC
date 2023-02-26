// Application to check default Updatable nature of JdbcRowSet - Update Operation.

package com.sk.JdbcRowSetDemo;

import java.io.BufferedReader;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetDefaultUpdatableNatureDemo2 {
	public static void main(String[] args) {
		JdbcRowSet rowSet = null;
		BufferedReader br = null;
		
		try {
			rowSet = RowSetProvider.newFactory().createJdbcRowSet();
			rowSet.setUrl("jdbc:mysql://localhost:3306/Saurabh");
			rowSet.setUsername("root");
			rowSet.setPassword("root");
			rowSet.setCommand("select * from emp");
			rowSet.execute();
			
			while(rowSet.next()) {
				float esal = rowSet.getFloat(3);
				if(esal < 20000) {
					rowSet.updateFloat(3, esal+500);
					rowSet.updateRow();
					System.out.println("Employee Updated Successfully!");
				}
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
