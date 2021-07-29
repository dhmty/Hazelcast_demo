package com.example.service;

import java.util.List;
import com.example.model.Student;

public interface StudentService {
	void insertStudent(Student sv);
	void deleteStudent(String id);
	void modifyStudent(Student sv);
	void insertStudents(List<Student> students);
	List<Student> getAllStudents();
	Student getStudentById(String idStudent);
	void clearCacheById(String id);
}