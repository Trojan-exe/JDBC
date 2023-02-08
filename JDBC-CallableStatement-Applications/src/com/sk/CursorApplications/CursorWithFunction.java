/*Function
 * create or replace function getAllStudents return SYS_REFCURSOR
 * AS
 * students SYS_REFCURSOR;
 * BEGIN
 *    open students for
 *        select * from student;
 *    return students;
 * END getAllStudents;
 * /
 */
package com.sk.CursorApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.internal.OracleTypes;

public class CursorWithFunction {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{? = call getAllStudents}");

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.execute();
			
			rs = (ResultSet)cst.getObject(1);
			
			System.out.println("\nStudent Details");
			System.out.println("SNO\tSNAME\tEADDR");
			System.out.println("-------------------------");
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\n");
			}
		} catch (Exception e) {
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
