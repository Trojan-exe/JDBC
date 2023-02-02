/* Stored Procedure
 	create or replace procedure getSal(no IN number, sal OUT float) AS
 	BEGIN
 		select ESAL into sal from emp where ENO = no;
 	END getSal;
 	/
  
 */

package com.sk.CallableStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import javax.sound.sampled.AudioFileFormat.Type;

public class SingleValueReadProcedure {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{call getSal(?,?)}");
			
			System.out.println("Enter Employee No : ");
			int eno = Integer.parseInt(br.readLine());
			
			cst.setInt(1, eno);
			cst.registerOutParameter(2, Types.FLOAT);
			cst.execute();
			
			System.out.println("Employee Salary : " + cst.getFloat(2));
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
