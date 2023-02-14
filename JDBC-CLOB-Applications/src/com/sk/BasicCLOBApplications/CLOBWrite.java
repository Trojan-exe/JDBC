package com.sk.BasicCLOBApplications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CLOBWrite {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			pst = con.prepareStatement("insert into EMPCLOB values(?,?)");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Employee No: ");
			int eno = Integer.parseInt(br.readLine());
			pst.setInt(1, eno);
			
			File file = new File("S:/SAURABH KUMATKAR/Self_ID(Aadhar) .pdf");
			fr = new FileReader(file);
			pst.setCharacterStream(2, fr, file.length());
			pst.executeUpdate();
			System.out.println("Document Inserted Succefully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				fr.close();
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
