package com.sk.service;

import com.sk.beans.Student;
import com.sk.dao.StudentDao;
import com.sk.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public String addStudent(Student std) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		String status = studentDao.add(std);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		Student std = studentDao.search(sid);
		return std;
	}

	@Override
	public String updateStudent(Student std) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		String status = studentDao.update(std);
		return status;
	}

	@Override
	public String deleteStudent(String sid) {
		StudentDao studentDao = StudentDaoFactory.getStudentDao();
		String status = studentDao.delete(sid);
		return status;
	}

}
