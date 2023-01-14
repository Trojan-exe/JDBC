package com.sk.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sk.beans.Student;
import com.sk.factory.StudentServiceFactory;
import com.sk.service.StudentService;

public class Test {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StudentService studentService = StudentServiceFactory.getStudentService();
			Student std = null;
			String studentID = "", studentName = "", studentAddr = "";
			
			while(true)
			{
				System.out.println("\n");
				System.out.println("1] Search Student Details");
				System.out.println("2] Add New Student");
				System.out.println("3] Update Student Details");
				System.out.println("4] Delete Student");
				System.out.println("5] Exit");
				System.out.println("Enter number of operation to perform: ");
				int operationChoice = Integer.parseInt(br.readLine());
				
				switch(operationChoice)
				{
					case 1:
						System.out.println("Enter Student ID: ");
						studentID = br.readLine();
						std = studentService.searchStudent(studentID);
						
						if(std == null)
							System.out.println("Student does not exist!!!");
						else {
							System.out.println("ID\tName\tAddress");
							System.out.println("---------------------------");
							System.out.print(std.getSid() + "\t");
							System.out.print(std.getSname() + "\t");
							System.out.print(std.getSaddr() + "\n");
						}
					break;
					
					case 2:
						System.out.println("Enter Student ID: ");
						studentID = br.readLine();
						System.out.println("Enter Student Name: ");
						studentName = br.readLine();
						System.out.println("Enter Student Address: ");
						studentAddr = br.readLine();
						
						std = studentService.searchStudent(studentID);
						if(std != null)
							System.out.println("Student with given ID already exists in Database !!!");
						else
						{
							std = new Student();
							std.setSid(studentID);
							std.setSname(studentName);
							std.setSaddr(studentAddr);
							
							String status = studentService.addStudent(std);
							System.out.println(status);
						}						
						
					break;
						
					case 3:
						System.out.println("Enter Student ID: ");
						studentID = br.readLine();
						std = studentService.searchStudent(studentID);
						if(std == null)
							System.out.println("Student does not Exist in the Database !!!");
						else
						{
							Student newStudent = new Student();
							newStudent.setSid(studentID);
							
							System.out.println("Enter New Name [Old Name : " + std.getSname() + "] : ");
							studentName = br.readLine();
							if(studentName != null && !studentName.equals(""))
								newStudent.setSname(studentName);
							else
								newStudent.setSname(std.getSname());
							
							System.out.println("Enter New Address [Old Address : " + std.getSaddr() + "] : ");
							studentAddr = br.readLine();
							if(studentAddr != null && !studentAddr.equals(""))
								newStudent.setSaddr(studentAddr);
							else
								newStudent.setSaddr(std.getSaddr());
							
							String status = studentService.updateStudent(newStudent);
							System.out.println(status);
						}
					break;
					
					case 4:
						System.out.println("Enter Student ID: ");
						studentID = br.readLine();
						
						std = studentService.searchStudent(studentID);
						if(std == null)
							System.out.println("Student with given ID does not exist in the Database !!!");
						else {
							String status = studentService.deleteStudent(studentID);
							System.out.println(status);
						}
					break;
					
					case 5:
						System.out.println("====================Thank you!!=============================");
						System.exit(0);
					break;
					
					default:
						System.out.println("Please provide valid number !!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
