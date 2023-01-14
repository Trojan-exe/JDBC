package com.sk.createTable;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class CreateTable1 {

	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("Driver Loading...");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		Statement st = con.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Table Name: ");
		String tName = br.readLine();
		String query = "create table "+tName+"(ENO number(3) primary key, ENAME VARCHAR2(10), ESAL float(5), EADDR VARCHAR2(10))";
		st.executeUpdate(query);
		System.out.println("Table "+tName+" is created successfully");
		st.close();
		con.close();
	}
}
