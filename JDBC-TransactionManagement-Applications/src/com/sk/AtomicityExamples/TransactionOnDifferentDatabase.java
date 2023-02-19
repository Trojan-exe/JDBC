// Application to transfer amount from account present in Oracle DB to account present in MySQL DB.

package com.sk.AtomicityExamples;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class TransactionOnDifferentDatabase {
	public static void main(String[] args) {
		Connection oracleCon = null;
		Connection mysqlCon = null;
		PreparedStatement oraclePst = null;
		PreparedStatement mysqlPst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			oracleCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			mysqlCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/Saurabh", "root", "root");
			oracleCon.setAutoCommit(false);
			mysqlCon.setAutoCommit(false);
			
			oraclePst = oracleCon.prepareStatement("update Account set BALANCE = BALANCE - ? where ACCNO = ?");
			mysqlPst = mysqlCon.prepareStatement("update Account set BALANCE = BALANCE + ? where ACCNO = ?");
			
			br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Source Account: ");
			String srcAcc = br.readLine();
			System.out.println("Destination Account: ");
			String destAcc = br.readLine();
			System.out.println("Transfer Amount: ");
			int amount = Integer.parseInt(br.readLine());
			
			oraclePst.setInt(1, amount);
			oraclePst.setString(2, srcAcc);
			mysqlPst.setInt(1, amount);
			mysqlPst.setString(2, destAcc);
			
			int rc1 = oraclePst.executeUpdate();
			int rc2 = mysqlPst.executeUpdate();
			
			if(rc1 == 1 && rc2 == 1)
			{
				oracleCon.commit();
				mysqlCon.commit();
				System.out.println(amount + " Rs Transferred Successfully");
			}else {
				oracleCon.rollback();
				mysqlCon.rollback();
				System.out.println("Transaction Failure");
			}
		} catch (Exception e) {
			try {
				oracleCon.rollback();
				mysqlCon.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			System.out.println("Transaction Failure");
			e.printStackTrace();
		}finally {
			try {
				oracleCon.close();
				mysqlCon.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
