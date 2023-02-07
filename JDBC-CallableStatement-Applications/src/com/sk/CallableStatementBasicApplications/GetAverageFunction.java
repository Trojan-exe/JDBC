/* Stored Procedure
 	create or replace function getAvg(no1 IN number, no2 IN number) return float 
 	AS
 	sal1 float;
 	sal2 float;
 	BEGIN
 		select ESAL into sa1l from emp where ENO = no1;
 		select ESAL into sa12 from emp where ENO = no2;
 		return (sal1+sal2)/2;
 	END getAvg;
 	/
  
 */

package com.sk.CallableStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class GetAverageFunction {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{? = call getAVG(?,?)}");
			
			System.out.println("Enter First Employee No : ");
			int feno = Integer.parseInt(br.readLine());
			
			System.out.println("Enter Second Employee No : ");
			int seno = Integer.parseInt(br.readLine());
			
			cst.setInt(2, feno);
			cst.setInt(3, seno);
			cst.registerOutParameter(1, Types.FLOAT);
			cst.execute();
			
			System.out.println("Average Employee Salary : " + cst.getFloat(1));
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
