//Update Records by using executeQuery() method. 
//In case of Type-1 driver, SQLException "No ResultSet was produced" will be raised.
//In case of Type-4 driver, default resultSet (i.e. empty resultSet) will be returned by executeQuery() method. But, updated rowCount will be stored.

package com.sk.updateRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRecord3 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;	
		ResultSet rs = null;
		
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
			rs = st.executeQuery(query);
			
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
