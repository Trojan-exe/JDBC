// Program to Insert Record into emp table

package com.sk.insertRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertRecord1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				System.out.println("Want to Insert Record ? (yes/no) : ");
				String addRecordChoice = br.readLine();
				
				if(addRecordChoice.equalsIgnoreCase("yes")) {
					System.out.println("Enter ENO: ");
					int eno = Integer.parseInt(br.readLine());
					System.out.println("Enter ENAME: ");
					String ename = br.readLine();
					System.out.println("Enter ESAL: ");
					float esal = Float.parseFloat(br.readLine());
					System.out.println("Enter EADDR: ");
					String eaddr = br.readLine();
					
					String query = "insert into emp values("+eno+",'"+ename+"',"+esal+",'"+eaddr+"')";
					st.executeUpdate(query);
					System.out.println("Record inserted successfully");
				}
				else
					break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}

}
