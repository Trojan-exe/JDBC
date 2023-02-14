package com.sk.BasicCLOBApplications;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CLOBRead {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BufferedReader br = null;
		FileWriter fw = null;
		Reader r = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			pst = con.prepareStatement("select * from EMPCLOB where ENO = ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Employee No: ");
			int eno = Integer.parseInt(br.readLine());
			pst.setInt(1, eno);
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Employee Fetched : " + rs.getInt(1));
			
			fw = new FileWriter("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-CLOB-Applications/CLOB-Documents/Aadhar.pdf");
			r = rs.getCharacterStream(2);
			
			int val = r.read();
			while(val != -1) {
				fw.write(val);
				val = r.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
