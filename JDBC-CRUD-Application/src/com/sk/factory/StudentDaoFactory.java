package com.sk.factory;

import com.sk.dao.StudentDao;
import com.sk.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static StudentDao studentDao;
	
	static {
		studentDao = new StudentDaoImpl();
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
