package com.sk.dynamicQueryGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class dynamicQueryGuiService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public dynamicQueryGuiService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean execute(String query){
		boolean status;
		try {
			status = st.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public ArrayList<String> getData()
	{
		ArrayList<String> data = new ArrayList<String>();
		try {
			rs = st.getResultSet();
			ResultSetMetaData rmd = rs.getMetaData();
			int colCount = rmd.getColumnCount();
			String Headers = "";
			
			for(int i = 1; i <= colCount; i++) {
				Headers = Headers + rmd.getColumnName(i) + "  ";
			}
			data.add(Headers);
			
			while(rs.next()) {
				String temp = "";
				for(int i = 1; i <= colCount; i++) {
					temp = temp + rs.getString(i) + "  ";
				}
				data.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int getRowCount()
	{
		int rowCount;
		try {
			rowCount = st.getUpdateCount();
		} catch (Exception e) {
			e.printStackTrace();
			rowCount = 0;
		}
		return rowCount;
	}
}
