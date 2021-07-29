package com.example.service.impl;

import java.util.List;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.service.StudentService;

@Service
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl implements StudentService {

	@Autowired
    StudentDao studentDao;

	@Override
	public void insertStudent(Student student) {
		studentDao.insertStudent(student);
	}


	@CacheEvict(value = "students")
	public void clearCacheById(String idStudent) {
	}

	@Override
	public void deleteStudent(String id) {
		System.out.println("Students Service Layer - Delete Student");
		studentDao.deleteStudent(id);
	}


	@Override
	public void modifyStudent(Student sv) {
		System.out.println("Students Service Layer - Modify Student");
		studentDao.modifyStudent(sv);
		//modifyCache(sv);
	}

	@Override
	public void insertStudents(List<Student> students) {
		studentDao.insertStudents(students);
	}

	@Override
	@Cacheable("students")
	public List<Student> getAllStudents() {
		System.out.println("Students Service Layer - Get All Students");
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentById(String id) {
		System.out.println("Students Service Layer - Find Student"+id);
		return studentDao.getStudentById(id);
	}
}