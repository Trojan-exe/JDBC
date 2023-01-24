// Type-1 Driver provided by Sun Microsystems support Updatable ResultSets.
// Type-4 Driver provided by Oracle does not support Updatable ResultSets.
// Type-4 Driver provided by MySQL support Updatable ResultSets.

package com.sk.UpdatableResultSetApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetUpdateOperation {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saurabh", "root", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp");
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(rs.next()) {
				float esal = rs.getFloat("ESAL");
				if(esal < 10000) {
					float esal_new = esal + 500;
					rs.updateFloat(3, esal_new);
					rs.updateRow();
					System.out.println(rs.getInt("ENO") + " Employee updated successfully");
				}
			}
		}catch(Exception e) {
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
