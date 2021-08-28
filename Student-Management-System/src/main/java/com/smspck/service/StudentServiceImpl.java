package com.smspck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

import com.smspck.model.Student;
import com.smspck.repository.StudentRepository;



@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public  List<Student> getAllStudents(){
		return  studentRepository.findAll();		
	}
	
	@Override
	public void saveStudent(Student student) {
		this.studentRepository.save(student);
		}

	@Override
	public Student getStudentByID(long id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if(optional.isPresent()) {
			student = optional.get();
		}else {
				throw new RuntimeException(" Student not found for id :: " + id);
	    }
		return student;
	}

	@Override
	public void deleteStudentById(long id) {
		this.studentRepository.deleteById(id);
	}

	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		
		 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		 Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1,  pageSize,sort);
		return this.studentRepository.findAll(pageable);
	}
	
	
	

}
