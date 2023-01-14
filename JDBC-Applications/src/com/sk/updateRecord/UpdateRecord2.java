//Update Records by using execute() method

package com.sk.updateRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRecord2 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;	
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			sc = new Scanner(System.in);
			
			System.out.println("Enter Bonus amount :");
			int bonus = sc.nextInt();
			System.out.println("Enter Salary Range : ");
			int salRange = sc.nextInt();
			
			String query = "update emp set ESAL = ESAL+" + bonus + " where ESAL<" + salRange;
			boolean b = st.execute(query);
			System.out.println(b);
			
			int rowCount = st.getUpdateCount();
			System.out.println("No. of rows updated : " + rowCount);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
				sc.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
