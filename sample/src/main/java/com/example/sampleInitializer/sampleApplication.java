package com.example.sampleInitializer;



import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.sample.StudentController.StudentController;
import com.example.sample.StudentDomain.Student;

//project is using spring not using spring boot 
public class sampleApplication {
	
	public static void main(String[] args) {
		
		
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("createdBeans.xml");
		
		StudentController controller = context.getBean(StudentController.class);	
		
		
		//printing student detail
		List<Student> students	= controller.getAllStudent();
		
		for(Student i : students) {
			
			System.out.print(i.getId()+" "+i.getName()+" "+i.getMark());
			System.out.println();
		}
	}

}
