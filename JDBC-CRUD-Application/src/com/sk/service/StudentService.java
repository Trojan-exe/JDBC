package com.sk.service;

import com.sk.beans.Student;

public interface StudentService {
	public String addStudent(Student std);
	public Student searchStudent(String sid);
	public String updateStudent(Student sid);
	public String deleteStudent(String sid);
}
