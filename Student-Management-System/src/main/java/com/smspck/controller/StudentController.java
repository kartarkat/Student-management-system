package com.smspck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smspck.model.Student;
import com.smspck.service.StudentService;

@Controller
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	//display list of students
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstname", "asc", model);		
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		//create model to bind form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "new_student";
	}
	
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student")Student student) {
		//save data to database
		studentService.saveStudent(student);
		return "redirect:/";
		
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id")long id, Model model) {
		
		//get student data from service
		Student student = studentService.getStudentByID(id);
		
		//set student as model attribute to prepoulate the form
		model.addAttribute("student",student);
		return "update_student";
	}
	
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id")long id) {
		
		//call delete method from service
		
		this.studentService.deleteStudentById(id);
		return "redirect:/";
		
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
		    @RequestParam("sortDir") String sortDir, Model model){
		int pageSize = 5;
		
		Page<Student> page = studentService.findPaginated(pageNo,  pageSize, sortField, sortDir);
		List<Student> listStudents =page.getContent();
		
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listStudents",listStudents);
		return "index";
		
	}
	
	
		
	}
	
	
	
	
	
	
	


