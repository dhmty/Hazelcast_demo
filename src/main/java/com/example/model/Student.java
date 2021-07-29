package com.example.model;
import java.io.Serializable;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private String studentID;
	private String name;
	private String phone;
	private String major;

	public Student() {
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [=" + studentID + ", Name=" + name + ", Phone=" + phone + ", Major="+ major+ "]";
	}
}