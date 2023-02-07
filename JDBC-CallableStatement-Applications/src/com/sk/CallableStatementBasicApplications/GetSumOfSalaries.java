/* Stored Procedure
 	create or replace function getSalSum(salRange IN number) return int
 	AS
 	totalSum int;
 	BEGIN
 		select sum(ESAL) into totalSum from emp where ESAL <= salRange;
 		return totalSum;
 	END getSalSum;
 	/
 */

package com.sk.CallableStatementBasicApplications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class GetSumOfSalaries {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{? = call getSalSum(?)}");
			
			System.out.println("Enter Salary Range : ");
			int salRange = Integer.parseInt(br.readLine());
			
			cst.setInt(2, salRange);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			
			System.out.println("Sum of Salaries : " + cst.getFloat(1));
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
