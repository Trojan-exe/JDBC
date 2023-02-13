package com.sk.BasicBLOBApplications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageWrite {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pst = null;
		FileInputStream fis = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			pst = con.prepareStatement("insert into empBlob values(?,?)");
			
			System.out.println("Enter Employee No : ");
			int eno = br.read();
			pst.setInt(1, eno);
			
			File file = new File("S:/IMAGES/Wallpapers/IMG-20191205-WA0002.jpg");
			fis = new FileInputStream(file);
			pst.setBinaryStream(2, fis, file.length());
			pst.executeUpdate();
			System.out.println("Image Stored Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
