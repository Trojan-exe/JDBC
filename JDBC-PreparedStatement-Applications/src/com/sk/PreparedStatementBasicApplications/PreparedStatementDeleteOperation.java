// Prepared Statement is supported by all drivers of all database vendors.

package com.sk.PreparedStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementDeleteOperation {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			pst = con.prepareStatement("delete from emp where ENO = ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				System.out.println("Employee Number  : ");
				int eno = Integer.parseInt(br.readLine());
				
				pst.setInt(1, eno);
				int rowCount = pst.executeUpdate();
				
				if(rowCount >= 1) {
					System.out.println("Employee " + eno + " Deleted Successfully");
				}else {
					System.out.println("Employee not Deleted Successfully!!!");
				}
				
				System.out.println("Want to delete more employee ? (yes/no) : ");
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
