package com.example.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.dao.StudentDao;

@Repository
public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insertStudent(Student sv) {
		String sql = "INSERT INTO student " +
				"(idStudent,nameSV,phone,major) VALUES (?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				sv.getStudentID(), sv.getName(),sv.getPhone(),sv.getMajor()
		});
	}
	
	@Override
	public void insertStudents(final List<Student> students) {
		String sql = "INSERT INTO student " + "(idStudent,nameSV,phone,major) VALUES (?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student student = students.get(i);
				ps.setString(1, student.getStudentID());
				ps.setString(2, student.getName());
				ps.setString(3, student.getPhone());
				ps.setString(4, student.getMajor());
			}
			public int getBatchSize() {
				return students.size();
			}
		});

	}

	@Override
	public void deleteStudent(String id) {
		System.out.println("StudentDao - delete Student "+id);
		String sql = "DELETE FROM student where idStudent = ?";
		Object[] args = new Object[] {id};
		getJdbcTemplate().update(sql,args);
	}

	@Override
	public void modifyStudent(Student sv) {
		System.out.println("StudentDao - update Student "+sv.getStudentID());
		String sql = "UPDATE student set nameSV=?,phone=?,major=? where idStudent=?";
		getJdbcTemplate().update(sql, new Object[]{
				sv.getName(),sv.getPhone(),sv.getMajor(),sv.getStudentID()
		});
	}

	@Override
	public List<Student> getAllStudents(){
		System.out.println("StudentDao - Get All Students");
		String sql = "SELECT * FROM student";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Student> result = new ArrayList<Student>();
		for(Map<String, Object> row:rows){
			Student sv = new Student();
			sv.setStudentID((String)row.get("idStudent"));
			sv.setName((String)row.get("nameSV"));
			sv.setPhone((String)row.get("phone"));
			sv.setMajor((String)row.get("major"));
			result.add(sv);
		}
		return result;
	}

	@Override
	public Student getStudentById(String idStudent) {
		String sql = "SELECT * FROM student WHERE idStudent = ?";
		return (Student)getJdbcTemplate().queryForObject(sql, new Object[]{idStudent}, new RowMapper<Student>(){
			@Override
			public Student mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Student sv = new Student();
				sv.setStudentID(rs.getString("idStudent"));
				sv.setName(rs.getString("nameSV"));
				sv.setPhone(rs.getString("phone"));
				sv.setMajor(rs.getString("major"));
				return sv;
			}
		});
	}
}