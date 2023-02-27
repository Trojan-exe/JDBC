// For all operations, make changes in xml file before writing them in database.

package com.sk.WebRowSetDemo;

import java.io.FileReader;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowInsertUpdateDeleteOperations {
	public static void main(String[] args) {
		WebRowSet rowSet = null;
		FileReader fr = null;
		try {
			rowSet = RowSetProvider.newFactory().createWebRowSet();
			rowSet.setUrl("jdbc:mysql://localhost:3306/Saurabh");
			rowSet.setUsername("root");
			rowSet.setPassword("root");
			rowSet.setCommand("select * from emp");
			rowSet.execute();
			
			fr = new FileReader("C:/Users/DELL/Desktop/Eclipse Workspaces/DurgaSoft-jdbc-applications-workspace/JDBC-RowSets-Applications/src/com/sk/WebRowSetDemo/xml_files/demo.xml");
			rowSet.readXml(fr);;
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
