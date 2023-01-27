package com.sk.PreparedStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementInsertOperation {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			pst = con.prepareStatement("Insert into emp values(?,?,?,?)");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				System.out.println("Employee Number  : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.println("Employee Name  : ");
				String ename = br.readLine();
				System.out.println("Employee Salary  : ");
				float esal = Float.parseFloat(br.readLine());
				System.out.println("Employee Address : ");
				String eaddr = br.readLine();
				
				pst.setInt(1, eno);
				pst.setString(2, ename);
				pst.setFloat(3, esal);
				pst.setString(4, eaddr);
				int rowCount = pst.executeUpdate();
				if(rowCount >= 1) {
					System.out.println("Employee " + eno + " Inserted Successfully");
				}else {
					System.out.println("Employee not Inserted Successfully!!!");
				}
				
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
