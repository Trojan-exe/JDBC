// Type-1 Driver provided by Sun Microsystems support Updatable ResultSets.
// Type-4 Driver provided by Oracle does not support Updatable ResultSets.
// Type-4 Driver provided by MySQL support Updatable ResultSets.

package com.sk.UpdatableResultSetApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetInsertOperation {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp");
			br = new BufferedReader(new InputStreamReader(System.in));
			rs.moveToInsertRow();
			
			while(true) {
				System.out.println("Employee Number  : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.println("Employee Name  : ");
				String ename = br.readLine();
				System.out.println("Employee Salary  : ");
				float esal = Float.parseFloat(br.readLine());
				System.out.println("Employee Address : ");
				String eaddr = br.readLine();
				
				rs.updateInt(1, eno);
				rs.updateString(2, ename);
				rs.updateFloat(3, esal);
				rs.updateString(4, eaddr);
				rs.insertRow();
				System.out.println("Employee Inserted Successfully");
				
				System.out.println("Want to add more employee ? (yes/no) : ");
				String choice = br.readLine();
			
				if(choice.equalsIgnoreCase("yes")) {
					continue;
				}else {
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
