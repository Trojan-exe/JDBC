package com.sk.BasicBLOBApplications;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImageRead {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		FileOutputStream fos = null;
		InputStream is = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			pst = con.prepareStatement("select * from empBlob where ENO = ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Employee No: ");
			int eno = Integer.parseInt(br.readLine());
			pst.setInt(1, eno);
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Employee No : " + rs.getInt(1));
			
			fos = new FileOutputStream("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-BLOB-Applications/Blob-images/trial_image1.jpg");
			is = rs.getBinaryStream(2);
			
			int val = is.read();
			while(val != -1) {
				fos.write(val);
				val = is.read();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				fos.close();
				rs.close();
				is.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
