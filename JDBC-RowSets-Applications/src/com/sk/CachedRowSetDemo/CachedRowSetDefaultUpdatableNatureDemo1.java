// CachedRowSet Default Updatable nature - Insert Operation.

package com.sk.CachedRowSetDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetDefaultUpdatableNatureDemo1 {

	public static void main(String[] args) {
		Connection con = null;
		CachedRowSet rowSet = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Saurabh", "root", "root");
			con.setAutoCommit(false);
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
			rowSet.setCommand("select * from emp");
			rowSet.execute(con);
			br = new BufferedReader(new InputStreamReader(System.in));
			rowSet.moveToInsertRow();
			
			while(true) {
				System.out.println("Enter Employee Number: ");
				int eno = Integer.parseInt(br.readLine());
				System.out.println("Enter Employee Name: ");
				String ename = br.readLine();
				System.out.println("Enter Employee Salary: ");
				float esal = Float.parseFloat(br.readLine());
				System.out.println("Enter Employee Address: ");
				String eaddr = br.readLine();
				
				rowSet.updateInt(1, eno);
				rowSet.updateString(2, ename);
				rowSet.updateFloat(3, esal);
				rowSet.updateString(4, eaddr);
				rowSet.insertRow();
				System.out.println("Employee Inserted Successfully!");
				
				System.out.println("Want to add more Employees [yes/no] ? ");
				String option = br.readLine();
				if(option.equalsIgnoreCase("yes")) {
					continue;
				}else {
					break;
				}
			}
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
