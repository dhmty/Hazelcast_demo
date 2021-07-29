package com.example;

import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class SpringBootHazelcastExample {
	
	@Autowired
	StudentService studentService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootHazelcastExample.class, args);
		StudentService studentService = context.getBean(StudentService.class);
		// Insert Employees in the Table
		Student sv= new Student();
		sv.setStudentID("162881");
		sv.setName("Doan Minh");
		sv.setPhone("0911711901");
		sv.setMajor("CNTT");

		Student sv2= new Student();
		sv2.setStudentID("162882");
		sv2.setName("Doan Nguyen");
		sv2.setPhone("0911911911");
		sv2.setMajor("ATTT");

		Student sv3= new Student();
		sv3.setStudentID("162883");
		sv3.setName("Minh Nguyen");
		sv3.setPhone("0912229793");
		sv3.setMajor("DTVT");


		studentService.insertStudent(sv);

		List<Student> students = new ArrayList<>();
		students.add(sv2);
		students.add(sv3);
		studentService.insertStudents(students);

		System.out.println("Main Class - First Time retrieving Student Record from Service Class");
		studentService.getAllStudents().forEach(student-> System.out.println(student.toString()));

		System.out.println("Main Class - Second Time onwards retrieving Student Record from Hazelcast");
		studentService.getAllStudents().forEach(student-> System.out.println(student.toString()));

	}
}