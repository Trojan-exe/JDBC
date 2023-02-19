package com.sk.AtomicityExamples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TransactionOnSameDatabase {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "root");
			con.setAutoCommit(false);
			st = con.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Source Account: ");
			String srcAcc = br.readLine();
			System.out.println("Destination Account: ");
			String destAcc = br.readLine();
			System.out.println("Transfer Amount: ");
			int amount = Integer.parseInt(br.readLine());
			
			int rowCount1 = st.executeUpdate("update Account set BALANCE = BALANCE - " + amount + " where ACCNO = '" + srcAcc + "'");
			int rowCount2 = st.executeUpdate("update Account set BALANCE = BALANCE + " + amount + " where ACCNO = '" + destAcc + "'");
			
			if(rowCount1 == 1 && rowCount2 == 1)
			{
				con.commit();
				System.out.println(amount + " Rs Transferred Successfully");
			}else {
				System.out.println("Transaction Failure");
				con.rollback();
			}
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
