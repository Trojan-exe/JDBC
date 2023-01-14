package com.sk.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcToHtml {
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
			data = data + "<html><body><table border='1' align='center'>";
			data = data + "<tr><th>ENO</th><th>ENAME</th><th>ESAL</th><th>EADDR</th></tr>";
			
			while(rs.next())
			{
				data = data + "<tr><td>" + rs.getInt("ENO") + "</td>";
				data = data + "<td>" + rs.getString("ENAME") + "</td>";
				data = data + "<td>" + rs.getFloat("ESAL") + "</td>";
				data = data + "<td>" + rs.getString("EADDR") + "</td></tr>";
			}
			data = data + "</table></body></html>";
			
			fos = new FileOutputStream("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-To-HTML&TextFile/emp.html");
			byte[] b = data.getBytes();
			fos.write(b);
			System.out.println("Data Inserted successfully");
			
		} catch (Exception e) {
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
