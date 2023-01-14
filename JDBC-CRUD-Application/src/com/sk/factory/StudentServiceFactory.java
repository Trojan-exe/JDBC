package com.sk.factory;

import com.sk.service.StudentService;
import com.sk.service.StudentServiceImpl;

public class StudentServiceFactory {
	private static StudentService studentService;
	
	static {
		studentService = new StudentServiceImpl();
	}
	public static StudentService getStudentService() {
		return studentService;
	}
}
