package com.sk.WebRowSetDemo;

import java.io.FileWriter;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetBasicDemo {

	public static void main(String[] args) {
		WebRowSet rowSet = null;
		FileWriter fw = null;
		try {
			rowSet = RowSetProvider.newFactory().createWebRowSet();
			rowSet.setUrl("jdbc:mysql://localhost:3306/Saurabh");
			rowSet.setUsername("root");
			rowSet.setPassword("root");
			rowSet.setCommand("select * from emp");
			rowSet.execute();
			
			fw = new FileWriter("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-RowSets-Applications/src/com/sk/WebRowSetDemo/xml_files/demo.xml");
			rowSet.writeXml(fw);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rowSet.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
