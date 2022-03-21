package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	void deleteStudent(Long id);
	Student getStudentById(Long id);
	List<Student> listStudents();
	Student saveStudent(Student student);
}
