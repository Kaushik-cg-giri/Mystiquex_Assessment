package com.example.sample.StudentController;

import java.util.Arrays;
import java.util.List;

import com.example.sample.StudentDomain.School;
import com.example.sample.StudentDomain.Student;


public class StudentController {

	public School school;
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public StudentController() {
		
		System.out.println("StudentController object created");
	}
	
	public   List<Student> getAllStudent(){
		
		//school.Welcome();
		
		List<Student> list = Arrays.asList(new Student(1,"Mayavi",400),
										   new Student(5,"Luttapi",900),
										   new Student(8,"Kuttasan",9870));
		return list;
	}


}
