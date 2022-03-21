package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/new")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "add_student";
	}
	
	@GetMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	
	@GetMapping("/")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.listStudents());
		return "students";
	}
	
	@PostMapping("/")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Student currentStudent = studentService.getStudentById(id);
		currentStudent.setId(id);
		currentStudent.setFirstname(student.getFirstname());
		currentStudent.setLastname(student.getLastname());
		currentStudent.setEmail(student.getEmail());
		studentService.saveStudent(currentStudent);
		return "redirect:/";
	}
		
}