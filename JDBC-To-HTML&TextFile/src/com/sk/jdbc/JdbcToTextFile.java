package com.sk.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcToTextFile {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FileOutputStream fos= null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp");
			String data = "";
			data = data + "ENO,ENAME,ESAL,EADDR\n";
			
			while(rs.next()) {
				data = data + rs.getInt("ENO") + ",";
				data = data + rs.getString("ENAME") + ",";
				data = data + rs.getFloat("ESAL") + ",";
				data = data + rs.getString("EADDR") + "\n";

			}
			
			fos = new FileOutputStream("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-To-HTML&TextFile/emp.txt");
			byte[] b = data.getBytes();
			fos.write(b);
			System.out.println("Data Inserted Successfully!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
