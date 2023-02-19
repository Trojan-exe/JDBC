// Type-1 Driver provided by Sun MicroSystems doesn't support Savepoint feature at all.
// Type-4 Driver provided by Oracle support only setSavePoint() method. It doesn't support releaseSavepoint() method.
// Type-4 Driver provided by MySQL support both setSavePoint() and releaseSavepoint() methods.

package com.sk.AtomicityExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavepointDemo {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			con.setAutoCommit(false);
			st = con.createStatement();
			
			st.executeUpdate("insert into student values('s1', 'aaa', 'pune')");
			Savepoint sp = con.setSavepoint();
			st.executeUpdate("insert into student values('s2', 'bbb', 'pune')");
			con.rollback(sp);
			// con.releaseSavepoint(sp);    If we use this statement instead of con.rollback(sp), all 3 records will get inserted.
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
