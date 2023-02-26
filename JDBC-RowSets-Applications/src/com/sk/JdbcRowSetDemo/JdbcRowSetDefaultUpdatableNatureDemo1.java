// Application to check default Updatable nature of JdbcRowSet - Insert Operation.

package com.sk.JdbcRowSetDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetDefaultUpdatableNatureDemo1 {
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
			rowSet.moveToInsertRow();
			
			br = new BufferedReader(new InputStreamReader(System.in));
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
