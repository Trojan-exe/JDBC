package com.sk.dropTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DropTable1 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			int rowCount1 = st.executeUpdate("create table emp(ENO number(3) primary key, ENAME VARCHAR2(10), ESAL float(5), EADDR VARCHAR2(10))");
			System.out.println(rowCount1);
			int rowCount2 = st.executeUpdate("drop table emp");
			System.out.println(rowCount2);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();  
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
