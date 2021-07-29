package com.example.dao;

import java.util.List;
import com.example.model.Student;

public interface StudentDao {
	void insertStudent(Student sv);
	void insertStudents(List<Student> students);
	void deleteStudent(String id);
	void modifyStudent(Student sv);
	List<Student> getAllStudents();
	Student getStudentById(String studentID);
}