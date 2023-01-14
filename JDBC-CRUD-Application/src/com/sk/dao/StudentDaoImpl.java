package com.sk.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sk.beans.Student;
import com.sk.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student std) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			int rowCount = st.executeUpdate("insert into Student values('"+std.getSid()+"','"+std.getSname()+"','"+std.getSaddr()+"')");
			if(rowCount == 1)
				status = "Record Inserted Successfully!";
			else 
				status = "Student Record not inserted !!!";
		}catch(Exception e) {
			e.printStackTrace();
			status = "Student Record not inserted !!!";
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student std = null;
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Student where SID='"+sid+"'");
			boolean b = rs.next();
			if(b) {
				std = new Student();
				std.setSid(rs.getString("SID"));
				std.setSname(rs.getString("SNAME"));
				std.setSaddr(rs.getString("SADDR"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String update(Student std) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			int rowCount = st.executeUpdate("update Student set SNAME='" + std.getSname() + "',SADDR='" + std.getSaddr() + "' where SID='" + std.getSid() + "'");
			if(rowCount == 1)
				status = "Record updated successfully!";
			else 
				status = "Record not updated !!!";
		}catch(Exception e) {
			e.printStackTrace();
			status = "Record not updated !!!";
		}
		return status;
	}

	@Override
	public String delete(String sid) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			int rowCount = st.executeUpdate("delete from Student where SID='"+sid+"'");
			if(rowCount == 1)
				status = "Student " + sid + " deleted successfully!";
			else
				status = "Student " + sid + " not deleted. Plz try again!!!";
		}catch(Exception e) {
			e.printStackTrace();
			status = "Student " + sid + " not deleted. Plz try again!!!";
		}
		return status;
	}

}
