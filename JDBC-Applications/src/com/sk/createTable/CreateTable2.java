package com.sk.createTable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable2 {

	public static void main(String[] args)throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		Statement st = con.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Table Name: ");
		String tName = br.readLine();
		String query = "create table " + tName + "(";
		String primaryKeys = "";
		int primaryKeyCount = 0;
		
		while(true)
		{
			System.out.println("Want to add column ? (Yes/No) : ");
			String columnAddChoice = br.readLine();
			
			if(columnAddChoice.equalsIgnoreCase("yes"))
			{
				System.out.println("Enter Column Name: ");
				String colName = br.readLine();
				System.out.println("Enter Column Type: ");
				String colType = br.readLine();
				System.out.println("Enter column size: ");
				int colSize = Integer.parseInt(br.readLine());
				query = query + colName + " " + colType + "(" + colSize + ")";
				
				System.out.println("is Primary Key? (Yes/No) : ");
				String primaryKeyOption = br.readLine();
				if(primaryKeyOption.equalsIgnoreCase("yes"))
				{
					if(primaryKeyCount > 0)
						primaryKeys = primaryKeys + ",";
					
					primaryKeys = primaryKeys + colName;
					primaryKeyCount = primaryKeyCount + 1;
				}		
				query = query + ",";
			}
			else
			{
				if(primaryKeys.length()>0)
					query = query + "primary key(" + primaryKeys + "))";
				else
				{
					query = query.substring(0, query.length()-1);
					query = query + ")";
				}
				break;
			}
		}
		System.out.println("query: " + query);
		st.executeUpdate(query);
		System.out.println("Table "+tName+" is created successfully");
		st.close();
		con.close();
	}
}
