/*Stored Procedure
 * create or replace procedure getAllEmployees(salRange IN float, emps OUT SYS_REFCURSOR)
 * AS
 * BEGIN
 * 		open emps for 
 *  		select * from emp where esal <= salRange;
 * END getAllEmployees;
 * /
 */

package com.sk.CursorApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

import oracle.jdbc.internal.OracleTypes;

public class CursorWithStoredProcedures {
	
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{call getAllEmployees(?,?)}");
			
			System.out.println("Enter Salary Range : ");
			int salRange = Integer.parseInt(br.readLine());
			
			cst.setInt(1, salRange);
			cst.registerOutParameter(2, OracleTypes.CURSOR);
			cst.execute();
			
			rs = (ResultSet)cst.getObject(2);
			
			System.out.println("\nEmployee Details");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("-------------------------");
			
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getFloat(3)+"\t");
				System.out.print(rs.getString(4)+"\n");
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
