// Name                                      Null?    Type
// ----------------------------------------- -------- ----------------------------
// SID                                       NOT NULL VARCHAR2(5)
// SNAME                                              VARCHAR2(20)
// SADDR                                              VARCHAR2(20)

package com.sk.AtomicityExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RollBackDemo {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			con.setAutoCommit(false);
			st = con.createStatement();
			
			st.executeUpdate("insert into student values('s1', 'aaa', 'pune')");
			st.executeUpdate("insert into student values('s2', s6, 'pune')");  // Invalid Inputs
			st.executeUpdate("insert into student values('s3', 'ccc', 'pune')");
			con.commit();
			System.out.println("Transaction Success");
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			System.out.println("Transaction Failure");
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
